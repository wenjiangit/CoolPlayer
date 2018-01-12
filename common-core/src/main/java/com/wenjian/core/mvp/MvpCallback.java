package com.wenjian.core.mvp;


import com.wenjian.core.mvp.base.MvpPresenter;

/**
 * Description: MvpCallback
 * Date: 2017/8/31
 *
 * @author jian.wen@ubtrobot.com
 */

public interface MvpCallback<V,P> extends MvpPresenter<V> {

    P createPresenter();

    P getPresenter();

    void setPresenter(P presenter);

    V createView();

    V getMvpView();

    void setMvpView(V view);

}
