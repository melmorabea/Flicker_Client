package com.mobiquity.flicker.core.presenter;

import com.mobiquity.flicker.core.error.FlickerException;
import com.mobiquity.flicker.core.ui.MvpView;

import rx.Observable;
import rx.SingleSubscriber;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

    private class ErrorInterceptingSingleSubscriber<T> extends SingleSubscriber<T> {

        private SingleSubscriber<T> wrapped;

        ErrorInterceptingSingleSubscriber(SingleSubscriber<T> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public void onSuccess(T value) {
            wrapped.onSuccess(value);
        }

        @Override
        public void onError(Throwable error) {
            if (error instanceof FlickerException) {
                wrapped.onError(error);
            } else {
                wrapped.onError(FlickerException.adapt(error));
            }
        }
    }

    RxSubscriptionsHelper rxSubscriptionsHelper;

    public BasePresenter(RxSubscriptionsHelper rxSubscriptionsHelper) {
        this.rxSubscriptionsHelper = rxSubscriptionsHelper;
    }

    protected <T> void subscribeToObservable(Observable<T> observable, SingleSubscriber<T> singleSubscriber) {
        rxSubscriptionsHelper.subscribe(observable, new ErrorInterceptingSingleSubscriber<>(singleSubscriber));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        rxSubscriptionsHelper.clear();
    }

}
