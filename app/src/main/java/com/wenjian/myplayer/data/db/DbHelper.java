package com.wenjian.myplayer.data.db;

import com.wenjian.myplayer.data.db.source.collection.CollectionDataSource;
import com.wenjian.myplayer.data.db.source.record.RecordDataSource;

/**
 * Description: DbHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public interface DbHelper {


    RecordDataSource getRecordDataSource();


    CollectionDataSource getCollectionDataSource();


}
