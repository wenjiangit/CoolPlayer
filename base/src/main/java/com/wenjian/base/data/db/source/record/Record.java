package com.wenjian.base.data.db.source.record;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Description: Record
 * Date: 2018/1/25
 *
 * @author jian.wen@ubtrobot.com
 */
@Entity
public class Record {

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String thumb;

    @ColumnInfo(name = "current_progress")
    private long currentProgress;

    @ColumnInfo(name = "update_time")
    private long updateTime;

    @ColumnInfo(name = "total_progress")
    private long totalProgress;

    @NonNull
    @PrimaryKey
    private String id;

    @Ignore
    private boolean checked = false;

    public Record(String title, String thumb, long currentProgress, long currentTime, long totalProgress, String id) {
        this.title = title;
        this.thumb = thumb;
        this.currentProgress = currentProgress;
        this.updateTime = currentTime;
        this.totalProgress = totalProgress;
        this.id = id;
    }

    public Record() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public long getCurrentProgress() {
        return this.currentProgress;
    }

    public void setCurrentProgress(long currentProgress) {
        this.currentProgress = currentProgress;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTotalProgress() {
        return this.totalProgress;
    }

    public void setTotalProgress(long totalProgress) {
        this.totalProgress = totalProgress;
    }

    @Override
    public String toString() {
        return "Record{" +
                "title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", currentProgress=" + currentProgress +
                ", updateTime=" + updateTime +
                ", totalProgress=" + totalProgress +
                ", id='" + id + '\'' +
                '}';
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
