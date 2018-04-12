package com.wenjian.mine.record;

import android.util.Log;


import com.wenjian.base.data.db.source.record.Record;
import com.wenjian.base.data.db.source.record.RecordRepository;
import com.wenjian.base.ui.AppPresenter;
import com.wenjian.base.utils.rx.RxUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description: RecordPresenter
 * Date: 2018/1/26
 *
 * @author jian.wen@ubtrobot.com
 */

public class RecordPresenter extends AppPresenter<RecordContract.View>
        implements RecordContract.Presenter {

    private static final String TAG = "RecordPresenter";

    private final RecordRepository mRecordRepository;

    public RecordPresenter() {
        this.mRecordRepository = RecordRepository.getInstance();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadData() {
        getView().showLoading();
        addDisposable(mRecordRepository.loadAll()
                .compose(RxUtils.<List<Record>>transfor())
                .subscribe(new Consumer<List<Record>>() {
                    @Override
                    public void accept(List<Record> recordList) throws Exception {
                        getView().hideLoading();
                        getView().onLoadSuccess(recordList);
                        getView().setEditEnable(recordList.size() != 0);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().hideLoading();
                        getView().showMessage(throwable.getMessage());
                    }
                }));
    }

    @Override
    public void deleteRecords(List<String> recordIds) {
        if (recordIds == null || recordIds.isEmpty()) {
            return;
        }
        long start = System.currentTimeMillis();
        for (String recordId : recordIds) {
            mRecordRepository.deleteRecordById(recordId);
        }
        long time = System.currentTimeMillis() - start;
        Log.d(TAG, "deleteRecords: " + time);
    }

}
