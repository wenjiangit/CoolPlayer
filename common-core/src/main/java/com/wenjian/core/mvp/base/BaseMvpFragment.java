package com.wenjian.core.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.wenjian.core.mvp.FragmentMvpDelegate;
import com.wenjian.core.mvp.FragmentMvpDelegateImpl;
import com.wenjian.core.mvp.MvpCallback;


/**
 * BaseMvpFragment
 *
 * @author douliu
 * @date 2017/8/31
 */

public abstract class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends Fragment
        implements MvpCallback<V, P> {

    private FragmentMvpDelegate mDelegate;

    public FragmentMvpDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = new FragmentMvpDelegateImpl<>(this);
        }
        return mDelegate;
    }

    private P presenter;
    private V view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDelegate().onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        getDelegate().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDelegate().onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        getDelegate().onStop();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getDelegate().onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    @Override
    public void attachView(V view) {
        getPresenter().attachView(view);
    }

    @Override
    public void detachView() {
        getPresenter().detachView();
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return view;
    }

    @Override
    public void setMvpView(V view) {
        this.view = view;
    }
}
