package com.wenjian.myplayer.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wenjian.myplayer.entity.Comment;

import java.util.List;

/**
 * Description: CommentInfo
 * Date: 2018/1/12
 *
 * @author jian.wen@ubtrobot.com
 */

public class CommentInfo {
    /**
     *  "pnum":1,
     "totalRecords":9,
     "records":20,
     "list":Array[9],
     "totalPnum":1
     */
    @Expose
    private int pnum;
    @Expose
    private int totalRecords;
    @Expose
    private int records;
    @Expose
    @SerializedName("list")
    private List<Comment> commentList;
    @Expose
    private int totalPnum;

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getTotalPnum() {
        return totalPnum;
    }

    public void setTotalPnum(int totalPnum) {
        this.totalPnum = totalPnum;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "pnum=" + pnum +
                ", totalRecords=" + totalRecords +
                ", records=" + records +
                ", commentList=" + commentList +
                ", totalPnum=" + totalPnum +
                '}';
    }
}
