package com.wenjian.myplayer.di;

import android.content.Context;

import com.wenjian.myplayer.AppExecutors;
import com.wenjian.myplayer.data.db.PlayerDatabase;
import com.wenjian.myplayer.data.db.source.collection.CollectionDataSource;
import com.wenjian.myplayer.data.db.source.collection.LocalCollectionDataSource;
import com.wenjian.myplayer.data.db.source.record.LocalRecordDataSource;
import com.wenjian.myplayer.data.db.source.record.RecordDataSource;

/**
 * Description: Injector
 * Date: 2018/2/28
 *
 * @author jian.wen@ubtrobot.com
 */

public class Injector {


    public static PlayerDatabase provideDatabase(Context context) {
        return PlayerDatabase.getInstance(context);
    }

    public static RecordDataSource provideRecordDataSource(Context context) {
        return LocalRecordDataSource.getInstance(provideDatabase(context).recordDao(), AppExecutors.getInstance());
    }

     public static CollectionDataSource provideCollectionDataSource(Context context) {
        return LocalCollectionDataSource.getInstance(provideDatabase(context).collectionDao(), AppExecutors.getInstance());
    }




}
