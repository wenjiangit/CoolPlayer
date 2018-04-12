package com.wenjian.myplayer;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wenjian.base.data.db.PlayerDatabase;
import com.wenjian.base.utils.Utils;


/**
 * Description: MoviesApp
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class MoviesApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据层

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }


        PlayerDatabase.init(this);
        ARouter.init(this);

        //初始化工具层
        Utils.init(this);
//        CrashUtils.init();
    }


}
