package com.wenjian.myplayer.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description: AppPrefsHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppPrefsHelper implements PrefsHelper {

    private static final String PREFS_NAME = "app_prefs";

    private final SharedPreferences mPreferences;

    private static volatile PrefsHelper sInstance;

    private AppPrefsHelper(Context context) {
        mPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    public static PrefsHelper getInstance(Context context) {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (AppPrefsHelper.class) {
            if (sInstance == null) {
                sInstance = new AppPrefsHelper(context);
            }
        }
        return sInstance;
    }


    /***************************************************************************/

    @Override
    public void saveUserHead(String uri) {
        putString(Key.USER_HEAD, uri);
    }

    @Override
    public String getUserHead() {
        return getString(Key.USER_HEAD);
    }

    interface Key {
        String USER_HEAD = "head";
    }


    private void putString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    private String getString(String key) {
        return mPreferences.getString(key, "");
    }


    private void putInt(String key, int value) {
        mPreferences.edit().putInt(key, value).apply();
    }


}
