package com.wenjian.myplayer;

import com.androidnetworking.AndroidNetworking;
import com.wenjian.core.utils.Utils;
import com.wenjian.myplayer.data.AppDataManager;

/**
 * Description: Application
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据层
        AppDataManager.init(this);
        //初始化工具层
        Utils.init(this);
        //初始化网络模块
        AndroidNetworking.initialize(this);
    }
}
