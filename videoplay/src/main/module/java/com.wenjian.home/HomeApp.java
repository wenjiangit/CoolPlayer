package com.wenjian.home;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wenjian.base.data.db.PlayerDatabase;
import com.wenjian.base.utils.Utils;

/**
 * Description: HomeApp
 * Date: 2018/4/9
 *
 * @author jian.wen@ubtrobot.com
 */

public class HomeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        PlayerDatabase.init(this);
        Utils.init(this);
    }
}
