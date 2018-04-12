package com.wenjian.base.utils;

import android.app.Application;
import android.content.Context;

/**
 * Description: Utils
 * Date: 2018/4/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class Utils {

    private static Context sAppContext;


    public static void init(Application application) {
        sAppContext = application;
    }


    public static Context getContext() {
        return sAppContext;
    }
}
