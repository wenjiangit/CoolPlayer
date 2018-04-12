package com.wenjian.base.data.db.source.record;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Description: RecordDao
 * Date: 2018/2/1
 *
 * @author jian.wen@ubtrobot.com
 */
@Dao
public interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveRecords(Record... records);

    @Query("SELECT * FROM Record")
    List<Record> getRecords();

    @Query("DELETE FROM Record")
    void deleteAllRecords();

    @Query("SELECT * FROM Record WHERE id =:id")
    Record getRecordById(String id);

    @Query("DELETE FROM Record WHERE id =:id")
    void deleteRecordById(String id);

    @Query("SELECT * FROM Record LIMIT 3")
    List<Record> getDisplayRecords();


}
