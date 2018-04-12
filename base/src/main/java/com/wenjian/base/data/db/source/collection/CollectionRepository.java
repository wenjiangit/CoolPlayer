package com.wenjian.base.data.db.source.collection;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Description: CollectionRepository
 * Date: 2018/3/6
 *
 * @author jian.wen@ubtrobot.com
 */

public class CollectionRepository {

    private final CollectionDataSource mDataSource;

    public CollectionRepository() {
        mDataSource = LocalCollectionDataSource.getInstance();
    }


    public Flowable<List<Collection>> loadAll() {
        return mDataSource.loadAll();
    }


    private static class Holder {
        private static final CollectionRepository INSTANCE = new CollectionRepository();
    }

    public static CollectionRepository getInstance() {
        return Holder.INSTANCE;
    }


}
