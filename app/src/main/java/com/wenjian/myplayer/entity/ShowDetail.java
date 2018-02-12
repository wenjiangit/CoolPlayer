package com.wenjian.myplayer.entity;

import android.net.Uri;
import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Description: 资源展示方式
 * Date: 2018/1/9
 *
 * @author jian.wen@ubtrobot.com
 */
public class ShowDetail implements MultiItemEntity {


    public static final int TYPE_BANNER = 1;
    public static final int TYPE_NORMAL = 2;
    public static final int TYPE_ADV = 3;

    private static final String SHOW_TYPE_BANNER = "banner";
    private static final String SHOW_TYPE_ADV = "adv";
    private static final String SHOW_TYPE_IN = "IN";
    private static final String SHOW_TYPE_INFO = "informationList";

               /* "showStyle":"",
                "loadType":"videoList",
                "changeOpenFlag":"false",
                "line":1,
                "showType":"banner",
                "childList":Array[8],
                "moreURL":"",
                "title":"Banner",
                "bigPicShowFlag":""*/

    @Expose
    private String showStyle;

    @Expose
    private String loadType;

    @Expose
    private String changeOpenFlag;

    @Expose
    private int line;

    @Expose
    private String showType;

    @Expose
    private List<VideoDetail> childList;

    @Expose
    private String moreURL;

    @Expose
    private String title;

    @Expose
    private String bigPicShowFlag;


    public String getShowStyle() {
        return showStyle;
    }

    public void setShowStyle(String showStyle) {
        this.showStyle = showStyle;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getChangeOpenFlag() {
        return changeOpenFlag;
    }

    public void setChangeOpenFlag(String changeOpenFlag) {
        this.changeOpenFlag = changeOpenFlag;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public List<VideoDetail> getChildList() {
        return childList;
    }

    public void setChildList(List<VideoDetail> childList) {
        this.childList = childList;
    }

    public String getMoreURL() {
        return moreURL;
    }

    public void setMoreURL(String moreURL) {
        this.moreURL = moreURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBigPicShowFlag() {
        return bigPicShowFlag;
    }


    public void setBigPicShowFlag(String bigPicShowFlag) {
        this.bigPicShowFlag = bigPicShowFlag;
    }

    @Override
    public String toString() {
        return "ShowDetail{" +
                "showStyle='" + showStyle + '\'' +
                ", loadType='" + loadType + '\'' +
                ", changeOpenFlag='" + changeOpenFlag + '\'' +
                ", line=" + line +
                ", showType='" + showType + '\'' +
                ", childList=" + childList +
                ", moreURL='" + moreURL + '\'' +
                ", title='" + title + '\'' +
                ", bigPicShowFlag='" + bigPicShowFlag + '\'' +
                '}';
    }

    /**
     * 获取分类列表id
     *
     * @return catalogId
     */
    public String getCatalogId() {
        if (TextUtils.isEmpty(moreURL)) {
            return null;
        }
        return Uri.parse(moreURL).getQueryParameter("catalogId");
    }

    @Override
    public int getItemType() {
        if (SHOW_TYPE_BANNER.equals(showType)) {
            return TYPE_BANNER;
        } else if (SHOW_TYPE_ADV.equals(showType)||SHOW_TYPE_INFO.equals(loadType)) {
            return TYPE_ADV;
        } else {
            return TYPE_NORMAL;
        }
    }

}
