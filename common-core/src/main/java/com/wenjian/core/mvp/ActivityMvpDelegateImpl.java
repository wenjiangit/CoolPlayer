package com.wenjian.core.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.core.mvp.base.MvpView;


/**
 * activity生命周期代理实现类
 *
 * @author douliu
 * @date 2017/8/31
 */

public class ActivityMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>> implements ActivityMvpDelegate{


    private MvpCallback<V, P> mMvpCallback;
    private MvpCallbackProxy<V, P> mCallbackProxy;

    public ActivityMvpDelegateImpl(MvpCallback<V, P> mvpCallback) {
        mMvpCallback = mvpCallback;
    }

    private MvpCallbackProxy<V, P> getCallbackProxy() {
        if (mCallbackProxy == null) {
            mCallbackProxy = new MvpCallbackProxy<>(mMvpCallback);
        }
        return mCallbackProxy;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getCallbackProxy().createView();
        getCallbackProxy().createPresenter();
        getCallbackProxy().attachView(getCallbackProxy().getMvpView());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        getCallbackProxy().detachView();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
