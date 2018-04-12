package com.wenjian.base.mvp.lifemvp;

import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wenjian.base.mvp.base.MvpPresenter;
import com.wenjian.base.utils.Preconditions;

/**
 * Description: LifeMvpActivity
 * Date: 2018/2/5
 *
 * @author jian.wen@ubtrobot.com
 */

public abstract class LifeMvpActivity<V, P extends MvpPresenter<V>> extends AppCompatActivity {

    private P mPresenter;
    private V mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAndBindMvp();
    }

    /**
     * 创建和绑定View和presenter
     */
    private void createAndBindMvp() {
        final V view = Preconditions.checkNotNull(createView(), "view is null");
        final P presenter = Preconditions.checkNotNull(createPresenter(), "presenter is null");
        presenter.attachView(view);
        if (presenter instanceof LifecycleObserver) {
            getLifecycle().addObserver((LifecycleObserver) presenter);
        } else {
            throw new RuntimeException(presenter + " must implement LifecycleObserver interface!");
        }
        this.mPresenter = presenter;
        this.mView = view;
    }

    /**
     * 创建Presenter实例
     *
     * @return P
     */
    public abstract P createPresenter();

    /**
     * 创建View的实例
     *
     * @return V
     */
    public abstract V createView();

    /**
     * 获取Presenter实例
     *
     * @return P
     */
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 获取View的实例
     *
     * @return V
     */
    public V getView() {
        return mView;
    }
}
