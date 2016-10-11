package com.mobiquity.flicker.core.lifecycle;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class ActivityProvider extends LifeCycleTracker.Empty {

    private WeakReference<Activity> activityWeakReference;

    @Override
    public void onActivityStarted(Activity activity) {
        clearReference();
        activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        clearReference();
    }

    public Activity getCurrentlyAttachedActivity() {
        if (activityWeakReference == null)
            return null;
        return activityWeakReference.get();
    }

    private void clearReference() {
        if (activityWeakReference == null)
            return;
        activityWeakReference.clear();
        activityWeakReference = null;
    }


}
