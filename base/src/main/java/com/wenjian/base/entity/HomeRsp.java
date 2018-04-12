package com.wenjian.base.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Description: HomeRsp
 * Date: 2018/1/9
 *
 * @author jian.wen@ubtrobot.com
 */

public class HomeRsp {

    @Expose
    private List<HotSearchVideo> hotSearchList;

    @Expose
    private List<ShowDetail> list;

    public List<HotSearchVideo> getHotSearchList() {
        return hotSearchList;
    }

    public void setHotSearchList(List<HotSearchVideo> hotSearchList) {
        this.hotSearchList = hotSearchList;
    }

    public List<ShowDetail> getList() {
        return list;
    }

    public void setList(List<ShowDetail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HomeRsp{" +
                "hotSearchList=" + hotSearchList +
                ", list=" + list +
                '}';
    }
}
