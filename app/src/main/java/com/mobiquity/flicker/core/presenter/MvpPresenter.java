package com.mobiquity.flicker.core.presenter;

import com.mobiquity.flicker.core.ui.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class MvpPresenter<V extends MvpView> {

    private WeakReference<V> viewVWeakReference;

    private void clearViewReference() {
        if (viewVWeakReference != null) {
            viewVWeakReference.clear();
            viewVWeakReference = null;
        }
    }

    public void onAttach(V view) {
        if (view == null) throw new IllegalArgumentException("View cannot be null!");
        clearViewReference();
        viewVWeakReference = new WeakReference<>(view);
    }

    public V view() {
        return viewVWeakReference == null ? null : viewVWeakReference.get();
    }

    public void onDetach() {
        clearViewReference();
    }

    public boolean isViewAttached() {
        return view() != null;
    }

    public void onViewLoaded() {
        // To be overridden so that presenters can start getting to work
    }

}
