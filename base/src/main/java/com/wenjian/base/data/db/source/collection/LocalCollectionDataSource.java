package com.wenjian.base.data.db.source.collection;

import android.support.annotation.NonNull;


import com.wenjian.base.AppExecutors;
import com.wenjian.base.data.db.PlayerDatabase;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;

/**
 * Description: LocalCollectionDataSource
 * Date: 2018/2/1
 *
 * @author jian.wen@ubtrobot.com
 */

public class LocalCollectionDataSource implements CollectionDataSource {

    private final CollectionDao mCollectionDao;
    private final AppExecutors mAppExecutors;

    private static volatile CollectionDataSource sInstance;

    private LocalCollectionDataSource(@NonNull CollectionDao collectionDao, @NonNull AppExecutors appExecutors) {
        mCollectionDao = collectionDao;
        mAppExecutors = appExecutors;
    }

    public static CollectionDataSource getInstance() {
        if (sInstance == null) {
            synchronized (LocalCollectionDataSource.class) {
                if (sInstance == null) {
                    sInstance = new LocalCollectionDataSource(PlayerDatabase.getInstance().collectionDao(), AppExecutors.getInstance());
                }
            }
        }
        return sInstance;
    }


    @Override
    public void saveList(java.util.Collection<Collection> dataList) {
        mCollectionDao.saveCollection(dataList.toArray(new Collection[0]));

    }

    @Override
    public void saveListAsync(final java.util.Collection<Collection> dataList) {
        mAppExecutors.forDiskIO(new Runnable() {
            @Override
            public void run() {
                saveList(dataList);
            }
        });

    }

    @Override
    public void saveSingle(final Collection data) {
        mAppExecutors.forDiskIO(new Runnable() {
            @Override
            public void run() {
                mCollectionDao.saveCollection(data);
            }
        });
    }

    @Override
    public Flowable<List<Collection>> loadAll() {
        return Flowable.fromCallable(new Callable<List<Collection>>() {
            @Override
            public List<Collection> call() throws Exception {
                return mCollectionDao.getCollections();
            }
        });
    }

    @Override
    public void loadAllAsync(final LoadCallback<Collection> callback) {
        final WeakReference<LoadCallback<Collection>> callbackReference = new WeakReference<>(callback);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Collection> collections = mCollectionDao.getCollections();
                mAppExecutors.forMainThread(new Runnable() {
                    @Override
                    public void run() {
                        if (collections != null) {
                            if (callbackReference.get() != null) {
                                callbackReference.get().onDataLoaded(collections);
                            }
                        } else {
                            if (callbackReference.get() != null) {
                                callbackReference.get().onDataNotAvailable();
                            }
                        }
                    }
                });
            }
        };
        mAppExecutors.forDiskIO(runnable);
    }

    @Override
    public void clearAll() {
        mAppExecutors.forDiskIO(new Runnable() {
            @Override
            public void run() {
                mCollectionDao.clear();
            }
        });
    }
}
