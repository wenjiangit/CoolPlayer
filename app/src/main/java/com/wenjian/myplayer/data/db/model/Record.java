package com.wenjian.myplayer.data.db.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ColumnIgnore;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.wenjian.myplayer.data.db.DbflowDatabase;

/**
 * Description: Record
 * Date: 2018/1/25
 *
 * @author jian.wen@ubtrobot.com
 */

@Table(database = DbflowDatabase.class)
public class Record extends BaseModel{

    @Column
    private String title;

    @Column
    private String thumb;

    @Column
    private long currentProgress;

    @Column
    private long currentTime;

    @Column
    private long totalProgress;

    @PrimaryKey
    private String id;

    @ColumnIgnore
    private boolean checked = false;

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

    public long getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
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
                ", currentTime=" + currentTime +
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
