package com.wenjian.home;

import com.wenjian.base.ui.BaseSimpleActivity;

/**
 * Description: HomeActivity
 * Date: 2018/4/9
 *
 * @author jian.wen@ubtrobot.com
 */

public class HomeActivity extends BaseSimpleActivity {

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initWindows() {
        super.initWindows();
        HomeFragment fragment = new HomeFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_content, fragment)
                .commit();
    }
}
