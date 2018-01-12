package com.wenjian.myplayer.ui.base;

import com.wenjian.core.ui.base.BaseActivity;
import com.wenjian.myplayer.ui.UiDelegate;
import com.wenjian.myplayer.ui.UiDelegateBase;

/**
 * Description: BaseSimpleActivity
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public abstract class BaseSimpleActivity extends BaseActivity implements UiDelegate {

    private UiDelegate mUiDelegate;

    private UiDelegate getUiDelegate() {
        if (mUiDelegate == null) {
            mUiDelegate = new UiDelegateBase(this);
        }
        return mUiDelegate;
    }

    @Override
    public void showLoading() {
        getUiDelegate().showLoading();
    }

    @Override
    public void hideLoading() {
        getUiDelegate().hideLoading();
    }

    @Override
    public void onError(int resId) {
        getUiDelegate().onError(resId);

    }

    @Override
    public void onError(String message) {
        getUiDelegate().onError(message);

    }

    @Override
    public void showMessage(String message) {
        getUiDelegate().showMessage(message);

    }

    @Override
    public void showMessage(int resId) {
        getUiDelegate().showMessage(resId);
    }
}
