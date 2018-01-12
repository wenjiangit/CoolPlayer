package com.wenjian.myplayer.ui;

import android.support.annotation.StringRes;

/**
 * Description: UiDelegate
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public interface UiDelegate {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

}
