package com.wenjian.base.data.db.source.record;

import com.wenjian.myplayer.data.db.source.DataSource;

import io.reactivex.Flowable;

/**
 * Description: RecordDataSource
 * Date: 2018/2/1
 *
 * @author jian.wen@ubtrobot.com
 */

public interface RecordDataSource extends DataSource<Record> {

    Flowable<Record> getRecordById(String id);

    void deleteRecordById(String id);

    void getDisplayRecords(LoadCallback<Record> callback);


}
