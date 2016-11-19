package org.protocoder;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import org.protocoderrunner.AppRunnerActivity;
import org.protocoderrunner.base.utils.MLog;

import java.util.ArrayList;

/**
 * Created by biquillo on 5/09/16.
 */
public class MyLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    private String TAG = MyLifecycleHandler.class.getSimpleName();

    // I use four separate variables here. You can, of course, just use two and
    // increment/decrement them instead of using four and incrementing them all.
    private int resumed;
    private int paused;
    private int started;
    private int stopped;

    static ArrayList<Activity> mRunningScripts = new ArrayList<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        String activityName = activity.getClass().getSimpleName();
        MLog.d(TAG, activityName);

        if (activityName.equals(AppRunnerActivity.class.getSimpleName())) {
            MLog.d(TAG, "added");
            mRunningScripts.add(activity);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        String activityName = activity.getClass().getSimpleName();
        MLog.d(TAG, activityName);

        /*
        boolean exist = false;
        for (int i = 0; i < mRunningScripts.size(); i++) {
            exist = exist | mRunningScripts.get(i).getClass().getSimpleName().equals(activityName);
        }
        */
        // mRunningScripts.clear();


        // if (activityName.equals(AppRunnerActivity.class.getSimpleName())) {
        // }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
        MLog.d("test", "application is in foreground: " + (resumed > paused));
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
        MLog.d("test", "application is visible: " + (started > stopped));
    }

    // And these two public static functions
    public boolean isApplicationVisible() {
        return started > stopped;
    }

    public boolean isApplicationInForeground() {
        return resumed > paused;
    }

    public boolean isScriptRunning() {
        return false;
    }

    public void closeAllScripts() {
        for (int i = 0; i < mRunningScripts.size(); i++) {
            Activity activity = mRunningScripts.get(0);
            if (activity != null) activity.finish();
        }
        mRunningScripts.clear();
    }

}