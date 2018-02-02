package com.wenjian.myplayer.data.db.source.record;

import com.wenjian.myplayer.data.db.source.DataSource;
import com.wenjian.myplayer.data.db.source.record.Record;

/**
 * Description: RecordDataSource
 * Date: 2018/2/1
 *
 * @author jian.wen@ubtrobot.com
 */

public interface RecordDataSource extends DataSource<Record> {

    Record getRecordById(String id);

    void deleteRecordById(String id);


}
