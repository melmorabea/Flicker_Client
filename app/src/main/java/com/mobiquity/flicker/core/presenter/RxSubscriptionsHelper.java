package com.mobiquity.flicker.core.presenter;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class RxSubscriptionsHelper {

    private CompositeSubscription compositeSubscription;
    private Scheduler observeScheduler;

    @Inject
    public RxSubscriptionsHelper(CompositeSubscription compositeSubscription, @Named("main_scheduler") Scheduler observeScheduler) {
        this.compositeSubscription = compositeSubscription;
        this.observeScheduler = observeScheduler;
    }

    <T> void subscribe(Observable<T> observable, final SingleSubscriber<T> callback) {
        compositeSubscription.add(observable.observeOn(observeScheduler)
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(T t) {
                        callback.onSuccess(t);
                    }
                }));
    }

    /**
     * Cleans all subscriptions when this helper is no longer needed
     */
    void clear() {
        compositeSubscription.unsubscribe();
    }

}
