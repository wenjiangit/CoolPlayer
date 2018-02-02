package com.wenjian.myplayer.data.db.source.collection;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Description: CollectionDao
 * Date: 2018/2/1
 *
 * @author jian.wen@ubtrobot.com
 */
@Dao
public interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCollection(Collection... collections);

    @Query("SELECT * FROM Collection")
    List<Collection> getCollections();

    @Query("DELETE FROM collection")
    void clear();

    @Query("SELECT * FROM Collection WHERE id =:id")
    Collection getCollectionById(String id);


}
