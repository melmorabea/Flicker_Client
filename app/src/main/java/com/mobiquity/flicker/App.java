package com.mobiquity.flicker;

import android.app.Application;

import com.mobiquity.flicker.core.lifecycle.ActivityProvider;
import com.mobiquity.flicker.core.lifecycle.LifeCycleTracker;
import com.mobiquity.flicker.core.lifecycle.LifeCycleTracker.Proxy;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class App extends Application {

    private static App self;

    private LifeCycleTracker.Proxy lifeCycleTracker;
    private ActivityProvider activityProvider;

    public static App get() {
        return self;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;

        initLeakCanary();
        initLifeCycleTracking();
    }

    private void initLifeCycleTracking() {
        lifeCycleTracker = new Proxy();
        activityProvider = new ActivityProvider();
        lifeCycleTracker.addTracker(activityProvider);
        registerActivityLifecycleCallbacks(lifeCycleTracker);
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public ActivityProvider getActivityProvider() {
        return activityProvider;
    }

}
