package com.wenjian.myplayer.data.network.model;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.xiao.nicevideoplayer.Clarity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: VideoInfo
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoInfo {
    @Expose
    private int couponNum;
    @Expose
    /**高清*/
    private String HDURL;
    @Expose
    private String downloadURL;
    @Expose
    private String description;
    @Expose
    private String pic;
    @Expose
    private String title;
    @Expose
    private String kuaiKan;
    @Expose
    /**标清*/
    private String smoothURL;
    @Expose
    private String duration;
    @Expose
    private int score;
    @Expose
    private int airTime;
    @Expose
    private String fastDataId;
    @Expose
    private String ultraClearURL;
    @Expose
    private String director;
    @Expose
    private String videoType;
    @Expose
    private String htmlURL;
    @Expose
    private List<ShowDetail> list;
    /**
     * 超清
     */
    @Expose
    private String SDURL;
    @Expose
    private String actors;
    @Expose
    private String canWatchFlag;
    @Expose
    private String collectionFalg;
    @Expose
    private String lastPlayTime;
    @Expose
    private String region;
    @Expose
    private String vipFlag;

    public void setCouponNum(int couponNum) {
        this.couponNum = couponNum;
    }

    public int getCouponNum() {
        return couponNum;
    }

    public void setHDURL(String HDURL) {
        this.HDURL = HDURL;
    }

    public String getHDURL() {
        return HDURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setKuaiKan(String kuaiKan) {
        this.kuaiKan = kuaiKan;
    }

    public String getKuaiKan() {
        return kuaiKan;
    }

    public void setSmoothURL(String smoothURL) {
        this.smoothURL = smoothURL;
    }

    public String getSmoothURL() {
        return smoothURL;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setAirTime(int airTime) {
        this.airTime = airTime;
    }

    public int getAirTime() {
        return airTime;
    }

    public void setFastDataId(String fastDataId) {
        this.fastDataId = fastDataId;
    }

    public String getFastDataId() {
        return fastDataId;
    }

    public void setUltraClearURL(String ultraClearURL) {
        this.ultraClearURL = ultraClearURL;
    }

    public String getUltraClearURL() {
        return ultraClearURL;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public String getHtmlURL() {
        return htmlURL;
    }


    public void setSDURL(String SDURL) {
        this.SDURL = SDURL;
    }

    public String getSDURL() {
        return SDURL;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getActors() {
        return actors;
    }

    public void setCanWatchFlag(String canWatchFlag) {
        this.canWatchFlag = canWatchFlag;
    }

    public String getCanWatchFlag() {
        return canWatchFlag;
    }


    public void setCollectionFalg(String collectionFalg) {
        this.collectionFalg = collectionFalg;
    }

    public String getCollectionFalg() {
        return collectionFalg;
    }

    public void setLastPlayTime(String lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public String getLastPlayTime() {
        return lastPlayTime;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setVipFlag(String vipFlag) {
        this.vipFlag = vipFlag;
    }

    public String getVipFlag() {
        return vipFlag;
    }

    public List<ShowDetail> getList() {
        return list;
    }

    public void setList(List<ShowDetail> list) {
        this.list = list;
    }

    public String getVideoSrc() {
        if (!TextUtils.isEmpty(SDURL)) {
            return SDURL;
        } else if (!TextUtils.isEmpty(HDURL)) {
            return HDURL;
        } else {
            return smoothURL;
        }
    }

    /**
     * 获取清晰度
     *
     * @return 清晰度列表
     */
    public List<Clarity> getClarity() {
        List<Clarity> result = new ArrayList<>();
        result.add(new Clarity("超清", "720", SDURL));
        result.add(new Clarity("高清", "480", HDURL));
        result.add(new Clarity("标清", "270", smoothURL));
        return result;
    }

}
