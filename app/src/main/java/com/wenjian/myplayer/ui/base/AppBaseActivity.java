package com.wenjian.myplayer.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.core.mvp.lifemvp.LifeMvpActivity;
import com.wenjian.myplayer.ui.UiDelegate;
import com.wenjian.myplayer.ui.UiDelegateBase;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: AppBaseActivity
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public abstract class AppBaseActivity<V extends AppView, P extends MvpPresenter<V>> extends LifeMvpActivity<V, P>
        implements AppView, UiDelegate {

    private Unbinder mUnbinder;
    private UiDelegate mUiDelegate;

    private UiDelegate getUiDelegate() {
        if (mUiDelegate == null) {
            mUiDelegate = new UiDelegateBase(this);
        }
        return mUiDelegate;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();
        if (initArgs(getIntent().getExtras())) {
            setContentView();
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    private void setContentView() {
        Object layout = getContentLayout();
        if (layout == null) {
            throw new NullPointerException("getContentLayout is null");
        }
        if (layout instanceof View) {
            super.setContentView((View) layout);
        } else if (layout instanceof Integer) {
            super.setContentView(((Integer) layout));
        } else {
            throw new IllegalArgumentException("getContentLayout return only view or layoutId");
        }
    }

    protected void setToolbarTitle(CharSequence title) {
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }
        }*/
    }

    /**
     * 初始化窗口
     */
    protected void initWindows() {

    }

    /**
     * 初始化参数
     *
     * @param bundle 参数集合
     * @return 如果参数处理成功返回true, 否则返回false
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 获取布局资源id
     *
     * @return 资源id
     */
    protected abstract Object getContentLayout();

    /**
     * 初始化控件
     */
    protected void initWidget() {
        mUnbinder = ButterKnife.bind(this);
    }

    protected void setUnbinder(Unbinder unbinder) {
        this.mUnbinder = unbinder;
    }

    /**
     * 初始化数据
     */
    protected void initData() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
