package com.mobiquity.flicker.core.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mobiquity.flicker.core.presenter.MvpPresenter;
import com.mobiquity.flicker.core.ui.MvpView;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public abstract class MvpFragment<P extends MvpPresenter> extends BaseFragment implements MvpView {

    private P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notifyPresenterViewLoaded();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        detachPresenter();
    }

    protected P presenter() {
        return presenter;
    }

    private void attachPresenter() {
        if (presenter == null)
            presenter = providePresenter();
        presenter.onAttach(this);
    }

    private void notifyPresenterViewLoaded() {
        if (presenter != null)
            presenter.onViewLoaded();
    }

    private void detachPresenter() {
        if (presenter != null)
            presenter.onDetach();
    }

    protected abstract P providePresenter();

}
