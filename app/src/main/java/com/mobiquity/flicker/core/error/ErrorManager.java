package com.mobiquity.flicker.core.error;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class ErrorManager {

    /**
     * Maps java exceptions to the approriate {@link FlickerException}
     */
    private static class ExceptionsInterceptor<T> implements Func1<Throwable, Observable<T>> {

        @Override
        public Observable<T> call(Throwable throwable) {
            return Observable.error(FlickerException.adapt(throwable));
        }

    }

    private ErrorManager() { /* No instances */ }

    public static <T> Observable<T> wrap(Observable<T> observable) {
        return observable.onErrorResumeNext(new ExceptionsInterceptor<T>());
    }

}
