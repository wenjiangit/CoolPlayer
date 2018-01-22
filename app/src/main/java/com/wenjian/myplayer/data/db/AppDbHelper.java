package com.wenjian.myplayer.data.db;

import android.content.Context;

import com.wenjian.myplayer.data.network.model.DaoMaster;
import com.wenjian.myplayer.data.network.model.DaoSession;
import com.wenjian.myplayer.data.network.model.ShowDetail;
import com.wenjian.myplayer.data.network.model.VideoDetail;

/**
 * Description: AppDbHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppDbHelper implements DbHelper {

    private DaoSession mDaoSession;

    private AppDbHelper() {
    }

    public void init(Context context) {
        mDaoSession = new DaoMaster(new DbOpenHelper(context).getWritableDb()).newSession();
    }

    @Override
    public void saveVideos(VideoDetail... videoDetails) {
        mDaoSession.getVideoDetailDao().insertOrReplaceInTx(videoDetails);
    }



    @Override
    public void saveShowDetail(ShowDetail... showDetails) {
        mDaoSession.getShowDetailDao().insertOrReplaceInTx(showDetails);
    }

    private static class Holder {
        private static final AppDbHelper INSTANCE = new AppDbHelper();
    }

    public static AppDbHelper getInstance() {
        return Holder.INSTANCE;
    }

}
