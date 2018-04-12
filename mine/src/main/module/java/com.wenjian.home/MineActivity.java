package com.wenjian.home;

import com.wenjian.base.ui.BaseSimpleActivity;
import com.wenjian.mine.MineFragment;

/**
 * Description: MineActivity
 * Date: 2018/4/9
 *
 * @author jian.wen@ubtrobot.com
 */

public class MineActivity extends BaseSimpleActivity {

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_mine;
    }

    @Override
    protected void initWindows() {
        super.initWindows();
        MineFragment fragment = new MineFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_content, fragment)
                .commit();
    }
}
