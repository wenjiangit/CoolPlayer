package com.wenjian.finder;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wenjian.base.utils.Utils;
import com.wenjian.home.BuildConfig;

/**
 * Description: FinderApp
 * Date: 2018/4/9
 *
 * @author jian.wen@ubtrobot.com
 */

public class FinderApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        Utils.init(this);
    }
}
