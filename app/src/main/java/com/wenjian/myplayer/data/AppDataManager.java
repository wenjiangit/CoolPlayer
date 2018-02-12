package com.wenjian.myplayer.data;

import android.content.Context;

import com.wenjian.myplayer.data.db.AppDbHelper;
import com.wenjian.myplayer.data.db.DbHelper;
import com.wenjian.myplayer.data.db.source.collection.CollectionDataSource;
import com.wenjian.myplayer.data.db.source.record.RecordDataSource;
import com.wenjian.myplayer.entity.ApiResponse;
import com.wenjian.myplayer.data.prefs.AppPrefsHelper;
import com.wenjian.myplayer.data.prefs.PrefsHelper;

import io.reactivex.Single;

/**
 * Description: AppDataManager
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppDataManager implements DataManager {

    private static Context sApplication;
    private final PrefsHelper mPrefsHelper;
    private final DbHelper mDbHelper;

    private AppDataManager() {
        mPrefsHelper = AppPrefsHelper.getInstance(sApplication);
        mDbHelper = AppDbHelper.getInstance(sApplication);
    }

    public static void init(Context context) {
        sApplication = context.getApplicationContext();
    }

    @Override
    public RecordDataSource getRecordDataSource() {
        return mDbHelper.getRecordDataSource();
    }

    @Override
    public CollectionDataSource getCollectionDataSource() {
        return mDbHelper.getCollectionDataSource();
    }

    @Override
    public void saveUserHead(String uri) {
        mPrefsHelper.saveUserHead(uri);
    }

    @Override
    public String getUserHead() {
        return mPrefsHelper.getUserHead();
    }

    private static class Holder {
        private static final AppDataManager INSTANCE = new AppDataManager();
    }

    public static AppDataManager getInstance() {
        if (sApplication == null) {
            throw new NullPointerException("you should call init first");
        }
        return Holder.INSTANCE;
    }

}
