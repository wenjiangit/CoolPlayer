package com.wenjian.core.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity的基类
 *
 * @author wenjian
 * @date 2017/6/2
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected Unbinder mUnbinder;

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
    @CallSuper
    protected void initWidget() {
        mUnbinder = ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

    }
}
