package com.wenjian.myplayer.data;

import android.content.Context;
import android.util.SparseArray;

import com.wenjian.myplayer.data.db.AppDbHelper;
import com.wenjian.myplayer.data.db.DbHelper;
import com.wenjian.myplayer.data.db.source.DataSource;
import com.wenjian.myplayer.data.db.source.collection.CollectionDataSource;
import com.wenjian.myplayer.data.db.source.record.RecordDataSource;
import com.wenjian.myplayer.data.network.ApiHelper;
import com.wenjian.myplayer.data.network.AppApiHelper;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.data.prefs.AppPrefsHelper;
import com.wenjian.myplayer.data.prefs.PrefsHelper;

import io.reactivex.Single;

/**
 * Description: AppDataManager
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppDataManager implements DataManager {

    private static Context sApplication;
    private final ApiHelper mApiHelper;
    private final PrefsHelper mPrefsHelper;
    private final DbHelper mDbHelper;

    private AppDataManager() {
        mApiHelper = AppApiHelper.create();
        mPrefsHelper = AppPrefsHelper.getInstance(sApplication);
        mDbHelper = AppDbHelper.getInstance(sApplication);
    }

    public static void init(Context context) {
        sApplication = context.getApplicationContext();
    }

    @Override
    public RecordDataSource getRecordDataSource() {
        return mDbHelper.getRecordDataSource();
    }

    @Override
    public CollectionDataSource getCollectionDataSource() {
        return mDbHelper.getCollectionDataSource();
    }

    @Override
    public void saveUserHead(String uri) {
        mPrefsHelper.saveUserHead(uri);
    }

    @Override
    public String getUserHead() {
        return mPrefsHelper.getUserHead();
    }

    private static class Holder {
        private static final AppDataManager INSTANCE = new AppDataManager();
    }

    public static AppDataManager getInstance() {
        if (sApplication == null) {
            throw new NullPointerException("you should call init first");
        }
        return Holder.INSTANCE;
    }

    @Override
    public Single<HttpResponse> doHomePagerApiCall() {
        return mApiHelper.doHomePagerApiCall();
    }

    @Override
    public Single<HttpResponse> doVideoInfoApiCall(String url) {
        return mApiHelper.doVideoInfoApiCall(url);
    }

    @Override
    public Single<HttpResponse> doCommentListApiCall(String mediaId, String pnum) {
        return mApiHelper.doCommentListApiCall(mediaId, pnum);
    }

    @Override
    public Single<HttpResponse> doVideoListApiCall(String catalogId, String pnum) {
        return mApiHelper.doVideoListApiCall(catalogId, pnum);
    }

    @Override
    public Single<HttpResponse> doVideoDetailApiCall(String mediaId) {
        return mApiHelper.doVideoDetailApiCall(mediaId);
    }

    @Override
    public Single<HttpResponse> doSimpleGetAction(String url) {
        return mApiHelper.doSimpleGetAction(url);
    }


}
