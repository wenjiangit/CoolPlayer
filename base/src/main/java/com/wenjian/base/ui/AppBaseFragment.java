package com.wenjian.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenjian.base.mvp.base.MvpPresenter;
import com.wenjian.base.mvp.lifemvp.LifeMvpFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: AppBaseFragment
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public abstract class AppBaseFragment<V extends AppView, P extends MvpPresenter<V>> extends LifeMvpFragment<V, P>
        implements AppView {

    /**
     * 标识是否是第一次初始化数据
     */
    private boolean mIsFirstInitData = true;

    private UiDelegate mUiDelegate;

    private View mRootView;

    private Unbinder mUnbinder;

    /**
     * 标识View是否已经完成初始化
     */
    private boolean isViewInited = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //如果View还没有初始化完成或者不可见,则不加载数据
        if (!isViewInited || !isVisibleToUser) {
            return;
        }

        //如果数据的第一次初始化还没有进行,则进行第一次加载
        doFirstLoadIfNeed();

        onLazyLoad();

    }

    /**
     * 如果有必要进行第一次数据加载
     */
    private void doFirstLoadIfNeed() {
        if (mIsFirstInitData) {
            mIsFirstInitData = false;
            onFirstLoad();
        }
    }

    /**
     * 每一次Fragment可见时都会加载
     */
    protected void onLazyLoad() {

    }

    /**
     * fragment可见时加载数据,只加载一次
     */
    protected void onFirstLoad() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
        if (context instanceof UiDelegate) {
            mUiDelegate = (UiDelegate) context;
        }
    }

    /**
     * 初始化参数信息
     *
     * @param arguments 参数信息
     */
    protected void initArgs(Bundle arguments) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflateContentView(inflater, container);
            initWidget(mRootView);
        } else {
            if (mRootView.getParent() != null) {
                ((ViewGroup) mRootView.getParent()).removeView(mRootView);
            }
        }
        return mRootView;
    }

    /**
     * 加载内容布局
     *
     * @param inflater  LayoutInflater
     * @param container ViewGroup
     * @return 根view
     */
    private View inflateContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View root;
        Object layout = getContentLayout();
        if (layout == null) {
            throw new NullPointerException("getContentLayout is null");
        }
        if (layout instanceof View) {
            root = ((View) layout);
        } else if (layout instanceof Integer) {
            root = inflater.inflate(((Integer) layout), container, false);
        } else {
            throw new IllegalArgumentException("getContentLayout return only view or layoutId");
        }
        return root;
    }

    /**
     * 初始化控件
     *
     * @param rootView 自己定义的布局
     */
    protected void initWidget(View rootView) {
        mUnbinder = ButterKnife.bind(this, rootView);
    }

    /**
     * 获取内容布局资源id或view
     *
     * @return 资源或view
     */
    protected abstract Object getContentLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewInited = true;
        if (getUserVisibleHint()) {
            doFirstLoadIfNeed();
            onLazyLoad();
        }

        //当布局初始化完成后开始初始化数据
        initData();
    }

    /**
     * 初始化数据,伴随Fragment生命周期
     */
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInited = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        if (mUiDelegate != null) {
            mUiDelegate.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mUiDelegate != null) {
            mUiDelegate.hideLoading();
        }
    }

    @Override
    public void onError(int resId) {
        if (mUiDelegate != null) {
            mUiDelegate.onError(resId);
        }
    }

    @Override
    public void onError(String message) {
        if (mUiDelegate != null) {
            mUiDelegate.onError(message);
        }

    }

    @Override
    public void showMessage(String message) {
        if (mUiDelegate != null) {
            mUiDelegate.showMessage(message);
        }
    }

    @Override
    public void showMessage(int resId) {
        if (mUiDelegate != null) {
            mUiDelegate.showMessage(resId);
        }

    }
}
