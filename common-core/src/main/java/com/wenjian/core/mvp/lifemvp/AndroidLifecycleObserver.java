package com.wenjian.core.mvp.lifemvp;

import android.arch.lifecycle.LifecycleObserver;
import android.util.Log;

/**
 * Description: AndroidLifecycleObserver
 * Date: 2018/2/2
 *
 * @author jian.wen@ubtrobot.com
 */

public class AndroidLifecycleObserver implements LifecycleObserver, AndroidLifecycle {

    private final String TAG = this.getClass().getSimpleName();


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");

    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");

    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");

    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");

    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onAny() {
        Log.d(TAG, "onAny: ");
    }
}
