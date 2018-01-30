package com.wenjian.myplayer.ui.record;

import android.util.Log;

import com.wenjian.core.mvp.base.BaseMvpPresenter;
import com.wenjian.myplayer.data.db.AppDbHelper;
import com.wenjian.myplayer.data.db.DbHelper;
import com.wenjian.myplayer.data.db.model.Record;

import java.util.List;

/**
 * Description: RecordPresenter
 * Date: 2018/1/26
 *
 * @author jian.wen@ubtrobot.com
 */

public class RecordPresenter extends BaseMvpPresenter<RecordContract.View>
        implements RecordContract.Presenter {

    private static final String TAG = "RecordPresenter";

    @Override
    public void loadData() {
        getView().showLoading();
        AppDbHelper.getInstance().loadAllAsync(Record.class, new DbHelper.QueryCallback<Record>() {
            @Override
            public void onQuerySuccess(List<Record> result) {
                getView().hideLoading();
                getView().setEditEnable(result!= null && !result.isEmpty());
                getView().onLoadSuccess(result);
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
            AppDbHelper.getInstance().deleteById(Record.class, recordId);
        }
        long time = System.currentTimeMillis() - start;
        Log.d(TAG, "deleteRecords: " + time);
    }

}
