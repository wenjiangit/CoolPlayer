package com.wenjian.base.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description: Comment
 * Date: 2018/1/12
 *
 * @author jian.wen@ubtrobot.com
 */

public class Comment {

    @Expose
    private String msg;

    @Expose
    @SerializedName("phoneNumber")
    private String nickName;

    @Expose
    private String dataId;

    @Expose
    private String userPic;

    @Expose
    private String time;

    @Expose
    private String likeNum;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "msg='" + msg + '\'' +
                ", nickName='" + nickName + '\'' +
                ", dataId='" + dataId + '\'' +
                ", userPic='" + userPic + '\'' +
                ", time='" + time + '\'' +
                ", likeNum='" + likeNum + '\'' +
                '}';
    }
}
