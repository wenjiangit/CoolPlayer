package com.wenjian.myplayer.ui.record;

import android.util.Log;

import com.wenjian.myplayer.data.db.source.record.Record;
import com.wenjian.myplayer.data.db.source.DataSource;
import com.wenjian.myplayer.ui.base.AppPresenter;

import java.util.List;

/**
 * Description: RecordPresenter
 * Date: 2018/1/26
 *
 * @author jian.wen@ubtrobot.com
 */

public class RecordPresenter extends AppPresenter<RecordContract.View>
        implements RecordContract.Presenter {

    private static final String TAG = "RecordPresenter";

    @SuppressWarnings("unchecked")
    @Override
    public void loadData() {
        getView().showLoading();
        getDataManager().getRecordDataSource().loadAllAsync(new DataSource.LoadCallback<Record>() {
            @Override
            public void onDataLoaded(List<Record> records) {
                if (!isActive()) {
                    return;
                }
                getView().hideLoading();
                getView().setEditEnable(true);
                getView().onLoadSuccess(records);
            }

            @Override
            public void onDataNotAvailable() {
                if (!isActive()) {
                    return;
                }
                getView().hideLoading();
                getView().setEditEnable(false);
            }

        });
    }

    @Override
    public void deleteRecords(List<String> recordIds) {
        if (recordIds == null || recordIds.isEmpty()) {
            return;
        }
        long start = System.currentTimeMillis();
        for (String recordId : recordIds) {
            getDataManager().getRecordDataSource().deleteRecordById(recordId);
        }
        long time = System.currentTimeMillis() - start;
        Log.d(TAG, "deleteRecords: " + time);
    }

}
