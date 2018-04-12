package com.wenjian.web;

/**
 * Description: 浏览器行为接口
 * Date: 2017/12/8
 *
 * @author jian.wen@ubtrobot.com
 */

public interface IChrome {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void stopLoading();


    /**
     * 设置当前进度
     *
     * @param progress 进度
     */
    void setLoadingProgress(int progress);


    /**
     * 显示错误界面
     */
    void showError();


    /**
     * 设置网页标题
     * @param title 标题
     */
    void setWebTitle(String title);
}
