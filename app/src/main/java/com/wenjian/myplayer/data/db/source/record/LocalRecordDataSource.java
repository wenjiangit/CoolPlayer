package com.wenjian.myplayer.data.db.source.record;

import android.support.annotation.NonNull;

import com.wenjian.myplayer.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;

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
    public Flowable<List<Record>> loadAll() {
        return Flowable.fromCallable(new Callable<List<Record>>() {
            @Override
            public List<Record> call() throws Exception {
                return mRecordDao.getRecords();
            }
        });
    }

    @Override
    public void loadAllAsync(LoadCallback<Record> callback) {
        final WeakReference<LoadCallback<Record>> callbackReference = new WeakReference<>(callback);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Record> records = mRecordDao.getRecords();
                postToMainThread(records, callbackReference);
            }
        };
        mAppExecutors.forDiskIO(runnable);
    }


    @Override
    public Flowable<Record> getRecordById(final String id) {
        return Flowable.fromCallable(new Callable<Record>() {
            @Override
            public Record call() throws Exception {
                return mRecordDao.getRecordById(id);
            }
        });
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
    public void getDisplayRecords(final LoadCallback<Record> callback) {
        final WeakReference<LoadCallback<Record>> callbackReference = new WeakReference<>(callback);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Record> records = mRecordDao.getDisplayRecords();
                postToMainThread(records, callbackReference);
            }
        };
        mAppExecutors.forDiskIO(runnable);
    }

    private void postToMainThread(final List<Record> records, final WeakReference<LoadCallback<Record>> callbackReference) {
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
