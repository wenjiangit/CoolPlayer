package com.wenjian.myplayer.data.db.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.wenjian.myplayer.data.db.DbflowDatabase;

/**
 * Description: Collection
 * Date: 2018/1/25
 *
 * @author jian.wen@ubtrobot.com
 */

@Table(database = DbflowDatabase.class)
public class Collection {

    @PrimaryKey
    private String id;

    @Column
    private String title;

    @Column
    private String thumb;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

}
