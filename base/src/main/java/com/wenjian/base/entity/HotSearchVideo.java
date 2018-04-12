package com.wenjian.base.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description: HotSearchVideo
 * Date: 2018/1/9
 *
 * @author jian.wen@ubtrobot.com
 */

public class HotSearchVideo {

    /*
    {
                "refCounter":1,
                "cnname":"xingjichuanyue",
                "siteId":"1",
                "simplename":"xjcy",
                "id":"ff8080815a5f91db015a68a763b750d5",
                "tagName":"星际穿越",
                "createdtime":"2017-02-23 09:48:04",
                "enname":""
            }
    * */

    @Expose
    private int refCounter;

    @SerializedName("cnname")
    @Expose
    private String cnName;

    @Expose
    private String siteId;

    @SerializedName("simplename")
    @Expose
    private String simpleName;

    @Expose
    private String id;

    @Expose
    private String tagName;

    @SerializedName("createdtime")
    @Expose
    private String createdTime;

    @SerializedName("enname")
    @Expose
    private String enName;


    public int getRefCounter() {
        return refCounter;
    }

    public void setRefCounter(int refCounter) {
        this.refCounter = refCounter;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Override
    public String toString() {
        return "HotSearchVideo{" +
                "refCounter=" + refCounter +
                ", cnName='" + cnName + '\'' +
                ", siteId='" + siteId + '\'' +
                ", simpleName='" + simpleName + '\'' +
                ", id='" + id + '\'' +
                ", tagName='" + tagName + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", enName='" + enName + '\'' +
                '}';
    }
}
