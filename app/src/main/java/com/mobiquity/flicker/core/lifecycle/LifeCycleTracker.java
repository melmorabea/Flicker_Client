package com.mobiquity.flicker.core.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public interface LifeCycleTracker extends ActivityLifecycleCallbacks {

    public class Proxy implements LifeCycleTracker {

        private List<LifeCycleTracker> watchers = new ArrayList<>();

        public void addTracker(LifeCycleTracker tracker) {
            watchers.add(tracker);
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            for (LifeCycleTracker watcher : watchers) {
                watcher.onActivityCreated(activity, savedInstanceState);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            for (LifeCycleTracker watcher : watchers) {
                watcher.onActivityStarted(activity);
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            for (LifeCycleTracker watcher : watchers) {
                watcher.onActivityResumed(activity);
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            for (LifeCycleTracker watcher : watchers) {
                watcher.onActivityPaused(activity);
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            for (LifeCycleTracker watcher : watchers) {
                watcher.onActivityStopped(activity);
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            for (LifeCycleTracker watcher : watchers) {
                watcher.onActivitySaveInstanceState(activity, outState);
            }
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            for (LifeCycleTracker watcher : watchers) {
                watcher.onActivityDestroyed(activity);
            }
        }
    }

    class Empty implements LifeCycleTracker {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }

}
