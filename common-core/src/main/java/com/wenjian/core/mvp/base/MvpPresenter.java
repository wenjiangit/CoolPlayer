package com.wenjian.core.mvp.base;

/**
 * Presenter接口
 *
 * @author wenjian
 * @date 2017/8/31
 */

public interface MvpPresenter<V> {

    /**
     * 关联view层
     *
     * @param view view层
     */
    void attachView(V view);

    /**
     * 取消关联
     */
    void detachView();

}
