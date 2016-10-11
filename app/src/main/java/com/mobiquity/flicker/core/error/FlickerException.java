package com.mobiquity.flicker.core.error;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class FlickerException extends Exception {

    @IntDef({NETWORK_ERROR,NO_DATA_ERROR,UNKNOWN_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {}

    public static final int NETWORK_ERROR = 1;
    public static final int NO_DATA_ERROR = 2;
    public static final int UNKNOWN_ERROR = 3;

    private int errorCode;
    private String errorMessage;

    public FlickerException(@ErrorCode int errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public FlickerException(@ErrorCode int errorCode, String errorMessage, Throwable original) {
        super(original);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public @ErrorCode int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static Throwable adapt(Throwable t) {
        if (t instanceof UnknownHostException || t instanceof SocketException || t instanceof SocketTimeoutException) {
            return new FlickerException(NETWORK_ERROR, "Please check your internet connection and try again", t);
        } else {
            return new FlickerException(UNKNOWN_ERROR, "Oops .. something went wrong!", t);
        }
    }

}
