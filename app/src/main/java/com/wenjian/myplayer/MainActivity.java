package com.wenjian.myplayer;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wenjian.base.ui.BaseSimpleActivity;
import com.wenjian.base.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wenjian
 */
public class MainActivity extends BaseSimpleActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final long DELAY_TIME = 2000;
    private static final int[] MENU_IDS = new int[]{
            R.id.action_home,
            R.id.action_finder,
            R.id.action_account
    };
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.vp_container)
    ViewPager mVpContainer;
    long exitTime;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        for (int i = 0; i < MENU_IDS.length; i++) {
            if (itemId == MENU_IDS[i]) {
                mVpContainer.setCurrentItem(i);
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        long current = System.currentTimeMillis();
        if (current - exitTime > DELAY_TIME) {
            ToastUtils.showShort("再按一次返回键退出程序!");
            exitTime = current;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mNavigation.setOnNavigationItemSelectedListener(this);

        Fragment homeFragment = (Fragment) ARouter.getInstance().build("/home/HomeFragment").navigation();
        Fragment finderFragment = (Fragment) ARouter.getInstance().build("/finder/FinderFragment").navigation();
        Fragment mineFragment = (Fragment) ARouter.getInstance().build("/mine/MineFragment").navigation();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(finderFragment);
        fragments.add(mineFragment);

        mVpContainer.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));

        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mNavigation.setSelectedItemId(MENU_IDS[position]);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        mVpContainer.setCurrentItem(0);
    }

    private static class MainPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> mFragmentList;

        MainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList != null ? mFragmentList.size() : 0;
        }
    }
}
