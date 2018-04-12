package com.wenjian.base.mvp.lifemvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Description: Android组件生命周期感知接口
 * Date: 2018/2/2
 *
 * @author jian.wen@ubtrobot.com
 */

public interface AndroidLifecycle {

    /**
     * 收到onCreate事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate();

    /**
     * 收到onStart事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart();

    /**
     * 收到onResume事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume();

    /**
     * 收到onPause事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause();

    /**
     * 收到onStop事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop();

    /**
     * 收到onDestroy事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy();

    /**
     * 收到任何生命周期的回调
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny();

}
