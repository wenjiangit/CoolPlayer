package com.wenjian.core.mvp.lifemvp;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.core.mvp.base.MvpView;

import java.lang.ref.WeakReference;

/**
 * Description: 感知组件生命周期的Presenter的基类
 * Date: 2018/2/5
 *
 * @author jian.wen@ubtrobot.com
 */

public class LifeMvpPresenter<V extends MvpView> extends AndroidLifecycleObserver implements MvpPresenter<V> {

    private WeakReference<V> mReference;

    @Override
    public void attachView(V view) {
        mReference = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        V view = mReference.get();
        if (view != null) {
            mReference.clear();
            view = null;
        }
    }

    /**
     * view是否是活动状态
     *
     * @return true表示view可用
     */
    public boolean isActive() {
        return mReference.get() != null;
    }

    public V getView() {
        return mReference.get();
    }

    @Override
    public void onDestroy() {
        detachView();
    }
}
