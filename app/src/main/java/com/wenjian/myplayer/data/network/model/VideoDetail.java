package com.wenjian.myplayer.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;



/**
 * Description: VideoDetail
 * Date: 2018/1/9
 *
 * @author jian.wen@ubtrobot.com
 */
public class VideoDetail implements Parcelable,VideoDisplay {

    /*
    * {
                        "airTime":2010,
                        "duration":"01:40:31",
                        "loadType":"video",
                        "score":0,
                        "angleIcon":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296614609066838.png",
                        "dataId":"1f7948116a4b4d16afd1d67484c4756e",
                        "description":"一道极强的亮光，将分处五大洲的8个人带到了一片未知所在。他们身背降落伞从高空坠落，继而落入一望无边的雨林。这群人中有车臣部队战士、以色列国防军、墨西哥特种部队成员、FBI通缉的要犯、日本黑帮稻川会头目以及一名与其他人格格不入的医生。他们彼此互不相识，也不知道为何被带到了这片陌生丛林。在罗伊斯（阿德连·布隆迪 Adrien Brody 饰）的带领下，众人开始朝制高点进发，最终发现他们竟然置身在一颗外星球中。与此同时，凶猛邪恶的猛兽接连向他们袭来，罗伊斯意识到，他们不过是一群外星生物选出的猎物，而这片雨林正是外星人的狩猎场。
　　危机四伏，这些无所依靠的人类不得不去面对身形巨大、嗜好杀戮的铁血战士……",
                        "loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=1f7948116a4b4d16afd1d67484c4756e",
                        "shareURL":"http://m.svipmovie.com/#/moviedetails/1f7948116a4b4d16afd1d67484c4756e",
                        "pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/07/12/1499829348897053082.jpg",
                        "title":"人类大战异形战士",
                        "roomId":""
                    }
    * */

    @Expose
    private int airTime;
    @Expose
    private String duration;
    @Expose
    private String loadType;
    @Expose
    private float score;
    @Expose
    private String angleIcon;
    @Expose
    private String dataId;
    @Expose
    private String description;
    @Expose
    private String loadURL;
    @Expose
    private String shareURL;
    @Expose
    private String pic;
    @Expose
    private String title;
    @Expose
    private String roomId;

    private String showTitle;


    public int getAirTime() {
        return airTime;
    }

    public void setAirTime(int airTime) {
        this.airTime = airTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAngleIcon() {
        return angleIcon;
    }

    public void setAngleIcon(String angleIcon) {
        this.angleIcon = angleIcon;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoadURL() {
        return loadURL;
    }

    public void setLoadURL(String loadURL) {
        this.loadURL = loadURL;
    }

    public String getShareURL() {
        return shareURL;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getThumb() {
        return getPic();
    }

    @Override
    public void setThumb(String thumb) {
        setPic(thumb);
    }

    @Override
    public String getId() {
        return getDataId();
    }

    @Override
    public void setId(String id) {
        setDataId(id);
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getShowTitle() {
        return this.showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }



    @Override
    public String toString() {
        return "VideoDetail{" +
                "airTime=" + airTime +
                ", duration='" + duration + '\'' +
                ", loadType='" + loadType + '\'' +
                ", score=" + score +
                ", angleIcon='" + angleIcon + '\'' +
                ", dataId='" + dataId + '\'' +
                ", description='" + description + '\'' +
                ", loadURL='" + loadURL + '\'' +
                ", shareURL='" + shareURL + '\'' +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.airTime);
        dest.writeString(this.duration);
        dest.writeString(this.loadType);
        dest.writeFloat(this.score);
        dest.writeString(this.angleIcon);
        dest.writeString(this.dataId);
        dest.writeString(this.description);
        dest.writeString(this.loadURL);
        dest.writeString(this.shareURL);
        dest.writeString(this.pic);
        dest.writeString(this.title);
        dest.writeString(this.roomId);
    }





    public VideoDetail() {
    }

    protected VideoDetail(Parcel in) {
        this.airTime = in.readInt();
        this.duration = in.readString();
        this.loadType = in.readString();
        this.score = in.readInt();
        this.angleIcon = in.readString();
        this.dataId = in.readString();
        this.description = in.readString();
        this.loadURL = in.readString();
        this.shareURL = in.readString();
        this.pic = in.readString();
        this.title = in.readString();
        this.roomId = in.readString();
    }

    public VideoDetail(int airTime, String duration, String loadType, float score, String angleIcon, String dataId, String description, String loadURL, String shareURL, String pic, String title, String roomId, String showTitle) {
        this.airTime = airTime;
        this.duration = duration;
        this.loadType = loadType;
        this.score = score;
        this.angleIcon = angleIcon;
        this.dataId = dataId;
        this.description = description;
        this.loadURL = loadURL;
        this.shareURL = shareURL;
        this.pic = pic;
        this.title = title;
        this.roomId = roomId;
        this.showTitle = showTitle;
    }

    public static final Parcelable.Creator<VideoDetail> CREATOR = new Parcelable.Creator<VideoDetail>() {
        @Override
        public VideoDetail createFromParcel(Parcel source) {
            return new VideoDetail(source);
        }

        @Override
        public VideoDetail[] newArray(int size) {
            return new VideoDetail[size];
        }
    };
}
