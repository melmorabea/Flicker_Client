package com.mobiquity.flicker.photosearch.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobiquity.flicker.R;
import com.mobiquity.flicker.core.ui.adapter.RecyclerViewClickListener;
import com.mobiquity.flicker.core.ui.fragment.MvpFragment;
import com.mobiquity.flicker.injection.DaggerAppComponent;
import com.mobiquity.flicker.photodetails.view.PhotoDetailsActivity;
import com.mobiquity.flicker.photosearch.model.dto.Photo;
import com.mobiquity.flicker.photosearch.presenter.PhotoSearchPresenter;
import com.mobiquity.flicker.util.ProgressDialogHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotoSearchFragment extends MvpFragment<PhotoSearchPresenter> implements PhotoSearchView,
        RecyclerViewClickListener<Photo> {

    private CountingIdlingResource idlingResource;


    @BindView(R.id.photo_search_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.photo_search_progress)
    ProgressBar progressBar;
    @BindView(R.id.photo_search_error_text_view)
    TextView errorTextView;


    private ProgressDialogHelper progressDialogHelper;
    private PhotoSearchAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        progressDialogHelper = new ProgressDialogHelper();
        if (context instanceof PhotoSearchActivity) {
            idlingResource = ((PhotoSearchActivity) context).getIdlingResource();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        recyclerView.setAdapter(null); // working around potential RecyclerView bug
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                presenter().loadMore();
            }
        });
        if (adapter != null) {
            showRecyclerView();
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected PhotoSearchPresenter providePresenter() {
        return DaggerAppComponent.create().photoSearchPresenter();
    }

    @Override
    public void showPhotos(List<Photo> photos, boolean loadMore) {
        showRecyclerView();
        idlingResource.decrement();

        if (adapter == null || !loadMore) {
            adapter = new PhotoSearchAdapter(photos, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.addItems(photos);
        }
    }

    private void showRecyclerView() {
        recyclerView.setVisibility(View.VISIBLE);
        progressDialogHelper.hide();
        progressBar.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressDialogHelper.show(getContext());
        idlingResource.increment();
    }

    @Override
    public void hideLoading() {
        progressDialogHelper.hide();
    }

    @Override
    public void showLoadMoreLoading() {
        errorTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadMoreLoading() {
        errorTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        progressDialogHelper.hide();
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(error);
    }

    public void onSearchTriggered(String queryString) {
        presenter().searchPhotos(queryString);
    }

    @Override
    public void onItemClicked(View view, Photo model) {
        startActivity(new Intent(getContext(), PhotoDetailsActivity.class)
            .putExtra("photo", model));
    }

}
