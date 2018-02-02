package com.wenjian.myplayer.data.db.source.record;

import android.support.annotation.NonNull;

import com.wenjian.myplayer.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;

/**
 * Description: LocalRecordDataSource
 * Date: 2018/2/1
 *
 * @author jian.wen@ubtrobot.com
 */

public class LocalRecordDataSource implements RecordDataSource {

    private final RecordDao mRecordDao;
    private final AppExecutors mAppExecutors;

    private static volatile RecordDataSource sInstance;

    private LocalRecordDataSource(@NonNull RecordDao recordDao, @NonNull AppExecutors appExecutors) {
        this.mRecordDao = recordDao;
        this.mAppExecutors = appExecutors;
    }

    public static RecordDataSource getInstance(@NonNull RecordDao recordDao, @NonNull AppExecutors appExecutors) {
        if (sInstance == null) {
            synchronized (LocalRecordDataSource.class) {
                if (sInstance == null) {
                    sInstance = new LocalRecordDataSource(recordDao, appExecutors);
                }
            }
        }
        return sInstance;
    }


    @Override
    public void saveList(final Collection<Record> records) {
        mAppExecutors.forDiskIO(new Runnable() {
            @Override
            public void run() {
                mRecordDao.saveRecords(records.toArray(new Record[0]));
            }
        });
    }

    @Override
    public void saveListAsync(final Collection<Record> dataList) {
        mAppExecutors.forDiskIO(new Runnable() {
            @Override
            public void run() {
                saveList(dataList);
            }
        });
    }

    @Override
    public void saveSingle(final Record record) {
        mAppExecutors.forDiskIO(new Runnable() {
            @Override
            public void run() {
                mRecordDao.saveRecords(record);
            }
        });
    }

    @Override
    public List<Record> loadAll() {
        return mRecordDao.getRecords();
    }

    @Override
    public void loadAllAsync(LoadCallback<Record> callback) {
        final WeakReference<LoadCallback<Record>> callbackReference = new WeakReference<>(callback);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Record> records = mRecordDao.getRecords();
                mAppExecutors.forMainThread(new Runnable() {
                    @Override
                    public void run() {
                        if (records != null) {
                            if (callbackReference.get() != null) {
                                callbackReference.get().onDataLoaded(records);
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
    public Record getRecordById(String id) {
        return mRecordDao.getRecordById(id);
    }

    @Override
    public void deleteRecordById(final String id) {
        mAppExecutors.forDiskIO(new Runnable() {
            @Override
            public void run() {
                mRecordDao.deleteRecordById(id);
            }
        });
    }

    @Override
    public void clearAll() {
        mAppExecutors.forDiskIO(new Runnable() {
            @Override
            public void run() {
                mRecordDao.deleteAllRecords();
            }
        });
    }
}
