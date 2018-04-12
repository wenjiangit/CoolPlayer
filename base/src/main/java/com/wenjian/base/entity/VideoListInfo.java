package com.wenjian.base.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Description: VideoListInfo
 * Date: 2018/1/18
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoListInfo {

    /**
     *  "adv":Object{...},
     "pnum":1,
     "totalRecords":30,
     "bannerList":[

     ],
     "records":30,
     "list":Array[30],
     "totalPnum":712
     */

    @Expose
    private int pnum;
    @Expose
    private int records;
    @Expose
    private int totalPnum;
    @Expose
    private int totalRecords;
    @Expose
    private List<VideoDetail> list;


    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotalPnum() {
        return totalPnum;
    }

    public void setTotalPnum(int totalPnum) {
        this.totalPnum = totalPnum;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<VideoDetail> getList() {
        return list;
    }

    public void setList(List<VideoDetail> list) {
        this.list = list;
    }




    @Override
    public String toString() {
        return "VideoListInfo{" +
                "pnum=" + pnum +
                ", records=" + records +
                ", totalPnum=" + totalPnum +
                ", totalRecords=" + totalRecords +
                ", list=" + list +
                '}';
    }
}
