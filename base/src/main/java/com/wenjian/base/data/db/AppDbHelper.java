package com.wenjian.base.data.db;


import com.wenjian.base.data.db.source.collection.CollectionDataSource;
import com.wenjian.base.data.db.source.record.RecordDataSource;

/**
 * Description: AppDbHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */
public class AppDbHelper implements DbHelper {

    private static volatile AppDbHelper INSTANCE;
    private final RecordDataSource mRecordDataSource;
    private final CollectionDataSource mCollectionDataSource;


    public AppDbHelper(RecordDataSource recordDataSource, CollectionDataSource collectionDataSource) {
        this.mRecordDataSource = recordDataSource;
        this.mCollectionDataSource = collectionDataSource;
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
