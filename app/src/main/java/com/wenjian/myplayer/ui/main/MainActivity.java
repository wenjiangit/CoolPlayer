package com.wenjian.myplayer.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.wenjian.core.utils.ToastUtils;
import com.wenjian.core.widget.NavHelper;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.ui.base.BaseSimpleActivity;
import com.wenjian.myplayer.ui.finder.FinderFragment;
import com.wenjian.myplayer.ui.home.HomeFragment;
import com.wenjian.myplayer.ui.mine.MineFragment;

import butterknife.BindView;

/**
 * @author wenjian
 */
public class MainActivity extends BaseSimpleActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        NavHelper.OnNavMenuChangedListener<Integer> {


    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    private NavHelper<Integer> mNavHelper;

    private static final long DELAY_TIME = 2000;

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mNavigation.setOnNavigationItemSelectedListener(this);

        mNavHelper = new NavHelper<>(
                this, getSupportFragmentManager(), R.id.lay_container, this
        );

        mNavHelper.add(R.id.action_home, new NavHelper.Tab<>(HomeFragment.class, R.string.title_home))
                .add(R.id.action_finder, new NavHelper.Tab<>(FinderFragment.class, R.string.title_finder))
                .add(R.id.action_account, new NavHelper.Tab<>(MineFragment.class, R.string.title_account));
    }


    @Override
    protected void initData() {
        Menu menu = mNavigation.getMenu();
        menu.performIdentifierAction(R.id.action_home, 0);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return mNavHelper.performMenuClick(item.getItemId());
    }

    @Override
    public void onNavTabChange(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {

    }

    long exitTime;

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
}
