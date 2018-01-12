package com.wenjian.core.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Description: Utils
 * Date: 2017/12/18
 *
 * @author jian.wen@ubtrobot.com
 */

public class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private Utils() {
    }

    public static void init(@NonNull Context context) {
        sApplication = (Application) context.getApplicationContext();
        Logger.init();
    }

    static Application getAppContext() {
        if (sApplication == null) {
            throw new NullPointerException("you need init first");
        }
        return sApplication;
    }
}
