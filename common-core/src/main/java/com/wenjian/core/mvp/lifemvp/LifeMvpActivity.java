package com.wenjian.core.mvp.lifemvp;

import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.core.utils.Preconditions;

/**
 * Description: LifeMvpActivity
 * Date: 2018/2/5
 *
 * @author jian.wen@ubtrobot.com
 */

public abstract class LifeMvpActivity<V, P extends MvpPresenter<V>> extends AppCompatActivity {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        V view = Preconditions.checkNotNull(createView(), "view is null");
        P presenter = Preconditions.checkNotNull(createPresenter(), "presenter is null");
        presenter.attachView(view);
        this.mPresenter = presenter;
        if (presenter instanceof LifecycleObserver) {
            getLifecycle().addObserver((LifecycleObserver) mPresenter);
        } else {
            throw new RuntimeException(presenter + " must implement LifecycleObserver interface!");
        }
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

    public P getPresenter() {
        return mPresenter;
    }
}
