package com.wenjian.core.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment的基类
 *
 * @author wenjian
 * @date 2017/6/2
 */

public abstract class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();

    protected View mRootView;
    protected Unbinder mUnbinder;

    /**
     * 标识是否是第一次初始化数据
     */
    private boolean mIsFirstInitData = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
        initArgs(getArguments());
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
        Log.i(TAG, "onCreateView: ");
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
        if (mIsFirstInitData) {
            mIsFirstInitData = false;
            onFirstInit();
        }

        //当布局初始化完成后开始初始化数据
        initData();
    }

    /**
     * 当第一次加载数据时触发
     */
    protected void onFirstInit() {
        Log.i(TAG, "onFirstInit: ");
    }

    /**
     * 获取内容布局资源id或view
     *
     * @return 资源或view
     */
    protected abstract Object getContentLayout();

    /**
     * 初始化控件
     *
     * @param rootView 自己定义的布局
     */
    protected void initWidget(View rootView) {
        mUnbinder = ButterKnife.bind(this, rootView);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    /**
     * 返回事件触发时调用,是否拦截activity的返回事件
     *
     * @return 默认返回false, 拦截则返回true
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }
}
