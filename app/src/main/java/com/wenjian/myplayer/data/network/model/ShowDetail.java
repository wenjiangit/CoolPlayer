package com.wenjian.myplayer.data.network.model;

import android.net.Uri;
import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Description: 资源展示方式
 * Date: 2018/1/9
 *
 * @author jian.wen@ubtrobot.com
 */
@Entity(nameInDb = "show_detail")
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

    @Property(nameInDb = "show_style")
    @Expose
    private String showStyle;

    @Property(nameInDb = "load_type")
    @Expose
    private String loadType;

    @Expose
    private String changeOpenFlag;

    @Expose
    private int line;

    @Property(nameInDb = "show_type")
    @Expose
    private String showType;

    @ToMany(referencedJoinProperty = "showTitle")
    @Expose
    private List<VideoDetail> childList;

    @Property(nameInDb = "more_url")
    @Expose
    private String moreURL;

    @Id
    @Expose
    private String title;

    @Expose
    private String bigPicShowFlag;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1420238924)
    private transient ShowDetailDao myDao;


    @Generated(hash = 1186380439)
    public ShowDetail() {
    }

    @Generated(hash = 1905538474)
    public ShowDetail(String showStyle, String loadType, String changeOpenFlag, int line,
            String showType, String moreURL, String title, String bigPicShowFlag) {
        this.showStyle = showStyle;
        this.loadType = loadType;
        this.changeOpenFlag = changeOpenFlag;
        this.line = line;
        this.showType = showType;
        this.moreURL = moreURL;
        this.title = title;
        this.bigPicShowFlag = bigPicShowFlag;
    }

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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1912873124)
    public List<VideoDetail> getChildList() {
        if (childList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VideoDetailDao targetDao = daoSession.getVideoDetailDao();
            List<VideoDetail> childListNew = targetDao._queryShowDetail_ChildList(title);
            synchronized (this) {
                if (childList == null) {
                    childList = childListNew;
                }
            }
        }
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

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 20549044)
    public synchronized void resetChildList() {
        childList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1252067201)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getShowDetailDao() : null;
    }
}
