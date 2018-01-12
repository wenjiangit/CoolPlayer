package com.wenjian.myplayer.data.prefs;

/**
 * Description: AppPrefsHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppPrefsHelper implements PrefsHelper {


    private AppPrefsHelper() {
    }

    private static class Holder {
        private static final AppPrefsHelper INSTANCE = new AppPrefsHelper();
    }

    public static AppPrefsHelper create() {
        return Holder.INSTANCE;
    }

}
