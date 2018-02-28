package com.wenjian.myplayer;

import android.app.Application;
import android.content.Context;

import com.wenjian.core.utils.Utils;
import com.wenjian.myplayer.data.AppDataManager;

/**
 * Description: MoviesApp
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class MoviesApp extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //初始化数据层
        AppDataManager.init(this);
        //初始化工具层
        Utils.init(this);
    }


    public static Context getContext() {
        return sContext;
    }


}
