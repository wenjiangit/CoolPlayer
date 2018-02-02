package com.wenjian.myplayer.data.db;

import android.content.Context;

import com.wenjian.myplayer.AppExecutors;
import com.wenjian.myplayer.data.db.source.collection.CollectionDataSource;
import com.wenjian.myplayer.data.db.source.collection.LocalCollectionDataSource;
import com.wenjian.myplayer.data.db.source.record.LocalRecordDataSource;
import com.wenjian.myplayer.data.db.source.record.RecordDataSource;

/**
 * Description: AppDbHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppDbHelper implements DbHelper {

    private final PlayerDatabase mDatabase;

    private final RecordDataSource mRecordDataSource;

    private final CollectionDataSource mCollectionDataSource;

    private static volatile AppDbHelper INSTANCE;


    private AppDbHelper(Context context) {
        mDatabase = PlayerDatabase.getInstance(context);
        mRecordDataSource = LocalRecordDataSource.getInstance(mDatabase.recordDao(), new AppExecutors());
        mCollectionDataSource = LocalCollectionDataSource.getInstance(mDatabase.collectionDao(), new AppExecutors());
    }


    public static DbHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDbHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppDbHelper(context);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public RecordDataSource getRecordDataSource() {
        return mRecordDataSource;
    }

    @Override
    public CollectionDataSource getCollectionDataSource() {
        return mCollectionDataSource;
    }


}
