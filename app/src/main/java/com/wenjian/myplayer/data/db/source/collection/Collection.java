package com.wenjian.myplayer.data.db.source.collection;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.wenjian.myplayer.entity.VideoDisplay;

/**
 * Description: Collection
 * Date: 2018/1/25
 *
 * @author jian.wen@ubtrobot.com
 */
@Entity
public class Collection implements VideoDisplay{

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String thumb;

    @ColumnInfo(name = "update_time")
    private Long updateTime = System.currentTimeMillis();

    public Collection() {
    }

   /* public Collection(@NonNull String id, String title, String thumb, Long updateTime) {
        this.id = id;
        this.title = title;
        this.thumb = thumb;
        this.updateTime = updateTime;
    }*/

    @Override
    @NonNull
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getThumb() {
        return this.thumb;
    }

    @Override
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    @Override
    public String toString() {
        return "Collection{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
