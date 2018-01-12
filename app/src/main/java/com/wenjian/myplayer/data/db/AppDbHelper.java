package com.wenjian.myplayer.data.db;

/**
 * Description: AppDbHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppDbHelper implements DbHelper {

    private AppDbHelper() {
    }

    private static class Holder {
        private static final AppDbHelper INSTANCE = new AppDbHelper();
    }

    public static AppDbHelper create() {
        return Holder.INSTANCE;
    }

}
