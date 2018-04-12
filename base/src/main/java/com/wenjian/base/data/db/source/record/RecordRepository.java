package com.wenjian.base.data.db.source.record;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Description: RecordRepository
 * Date: 2018/3/6
 *
 * @author jian.wen@ubtrobot.com
 */

public class RecordRepository {

    private final RecordDataSource mDataSource;

    private RecordRepository(RecordDataSource dataSource) {
        mDataSource = dataSource;
    }

    public Flowable<List<Record>> loadAll() {
        return mDataSource.loadAll();
    }

    public void deleteRecordById(String id) {
        mDataSource.deleteRecordById(id);
    }


    private RecordRepository() {
        this(LocalRecordDataSource.getInstance());
    }
    private static class Holder {
        private static final RecordRepository INSTANCE = new RecordRepository();
    }

    public static RecordRepository getInstance() {
        return Holder.INSTANCE;
    }


}
