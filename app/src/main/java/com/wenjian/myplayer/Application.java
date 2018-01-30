package com.wenjian.myplayer;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.wenjian.core.utils.Utils;
import com.wenjian.myplayer.data.AppDataManager;
import com.wenjian.myplayer.data.db.AppDbHelper;

/**
 * Description: Application
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class Application extends android.app.Application {


    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //初始化数据层
        AppDataManager.init(this);
        //初始化工具层
        Utils.init(this);
        //初始化网络模块
        AndroidNetworking.initialize(this);

        AndroidNetworking.enableLogging();

        FlowManager.init(this);

    }


    public static Context getContext() {
        return sContext;
    }


}
