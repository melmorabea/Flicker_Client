package com.mobiquity.flicker.photodetails.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiquity.flicker.R;
import com.mobiquity.flicker.core.ui.PicassoHelper;
import com.mobiquity.flicker.core.ui.fragment.MvpFragment;
import com.mobiquity.flicker.injection.DaggerAppComponent;
import com.mobiquity.flicker.photodetails.presenter.PhotoDetailsPresenter;
import com.mobiquity.flicker.photosearch.model.dto.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotoDetailsFragment extends MvpFragment<PhotoDetailsPresenter> implements PhotoDetailsView {

    @BindView(R.id.photo_details_img)
    ImageView imageView;
    @BindView(R.id.photo_details_title)
    TextView titleTextView;

    private Photo photo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            photo = savedInstanceState.getParcelable("photo");
    }

    @Override
    public void onAttach(Context context) {
        extractPhoto(getActivity());
        super.onAttach(context);
    }

    private void extractPhoto(Activity activity) {
        if (activity.getIntent() == null)
            return;
        photo = activity.getIntent().getParcelableExtra("photo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.photo_details_img)
    void handleImageClicked() {
        if (titleTextView.getVisibility() == View.VISIBLE) {
            titleTextView.setVisibility(View.GONE);
        } else {
            titleTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected PhotoDetailsPresenter providePresenter() {
        return DaggerAppComponent.create().photoDetailsPresenter();
    }

    @Override
    public Photo getArgument() {
        return photo;
    }

    @Override
    public void showPhoto(Photo photo) {
        titleTextView.setText(photo.getTitle());
        PicassoHelper.loadImage(getContext(), photo.getUrl(), imageView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("photo", photo);
    }
}
