package com.mobiquity.flicker;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary();
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG && LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

}
