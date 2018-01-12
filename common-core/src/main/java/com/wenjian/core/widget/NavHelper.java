package com.wenjian.core.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

/**
 * 底部导航菜单辅助工具类
 *
 * @author wenjian
 * @date 2017/6/4
 */

public class NavHelper<T> {

    private final Context mContext;
    private final FragmentManager mFragmentManager;
    private final int mContainerId;
    private final OnNavMenuChangedListener<T> mListener;
    private SparseArray<Tab<T>> mTabs = new SparseArray<>();
    private Tab<T> mCurrentTab;

    public NavHelper(Context context, FragmentManager fragmentManager, int containerId, OnNavMenuChangedListener<T> listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = containerId;
        mListener = listener;
    }

    public NavHelper<T> add(int menuId, Tab<T> tab) {
        mTabs.put(menuId, tab);
        return this;
    }

    /**
     * 处理菜单点击事件
     * @param itemId 选中的菜单id
     * @return 是否能够处理这次事件
     */
    public boolean performMenuClick(int itemId) {
        Tab<T> tab = mTabs.get(itemId);
        if (tab != null) {
            doTabSelect(tab);
            return true;
        }
        return false;
    }

    private void doTabSelect(Tab<T> tab) {
        Tab<T> oldTab = null;
        if (mCurrentTab != null) {
            oldTab = mCurrentTab;
            if (oldTab == tab) {
                doTabReselected(tab);
                return;
            }
        }
        mCurrentTab = tab;
        doTabChanged(mCurrentTab, oldTab);
    }

    public Tab<T> getCurrent() {
        return mCurrentTab;
    }

    /**
     * 真正处理Fragment的切换
     * @param newTab 新的Tab
     * @param oldTab 旧的Tab
     */
    private void doTabChanged(Tab<T> newTab, Tab<T> oldTab) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (oldTab != null) {
            if (oldTab.fragment != null) {
                transaction.detach(oldTab.fragment);
            }
        }

        if (newTab.fragment != null) {
            transaction.attach(newTab.fragment);
        } else {
            Fragment fragment = Fragment.instantiate(mContext, newTab.clazz.getName(), null);
            transaction.add(mContainerId, fragment);
            newTab.fragment = fragment;
        }
        transaction.commit();
        //通知回调
        notifyTabChanged(newTab, oldTab);
    }

    /**
     * 回调菜单变化结果
     * @param newTab 选中的Tab
     * @param oldTab 之前的Tab
     */
    private void notifyTabChanged(Tab<T> newTab, Tab<T> oldTab) {
        if (mListener != null) {
            mListener.onNavTabChange(newTab, oldTab);
        }
    }

    /**
     * 重复点击同一个Tab
     * @param tab Tab
     */
    private void doTabReselected(Tab<T> tab) {
        // TODO: 2017/6/4 重复点击同一个tab所做的操作
    }

    /**
     * 菜单变化监听回调接口
     * @param <T>
     */
    public interface OnNavMenuChangedListener<T> {
        void onNavTabChange(Tab<T> newTab, Tab<T> oldTab);
    }

    public static class Tab<T> {

        final Class<?> clazz;
        final T extra;
        Fragment fragment;

        public Tab(Class<?> clazz, T extra) {
            this.clazz = clazz;
            this.extra = extra;
        }
    }

}
