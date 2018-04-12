package com.wenjian.base.data;


import com.wenjian.base.data.db.DbHelper;
import com.wenjian.base.data.db.source.collection.CollectionDataSource;
import com.wenjian.base.data.db.source.record.RecordDataSource;
import com.wenjian.base.data.prefs.PrefsHelper;

/**
 * Description: AppDataManager
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */
public class AppDataManager implements DataManager {

    private final PrefsHelper mPrefsHelper;
    private final DbHelper mDbHelper;

    public AppDataManager(PrefsHelper prefsHelper,DbHelper dbHelper) {
        this.mPrefsHelper = prefsHelper;
        this.mDbHelper = dbHelper;
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

}
