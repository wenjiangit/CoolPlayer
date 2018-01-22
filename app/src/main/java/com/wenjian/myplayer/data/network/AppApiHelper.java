package com.wenjian.myplayer.data.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.wenjian.myplayer.data.network.model.HttpResponse;

import io.reactivex.Single;

/**
 * Description: AppApiHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppApiHelper implements ApiHelper {

    private AppApiHelper() {
    }

    private static class Holder {

        private static final AppApiHelper INSTANCE = new AppApiHelper();
    }

    public static AppApiHelper create() {
        return Holder.INSTANCE;
    }

    @Override
    public Single<HttpResponse> doHomePagerApiCall() {
        return Rx2AndroidNetworking
                .get(ApiEndPoint.HOME_PAGER)
                .build()
                .getObjectSingle(HttpResponse.class);
    }

    @Override
    public Single<HttpResponse> doVideoInfoApiCall(String url) {
        return doSimpleGetAction(url);
    }

    @Override
    public Single<HttpResponse> doCommentListApiCall(String mediaId, String pnum) {
        return Rx2AndroidNetworking
                .get(ApiEndPoint.VIDEO_COMMENT)
                .addQueryParameter("mediaId", mediaId)
                .addQueryParameter("pnum", pnum)
                .build()
                .getObjectSingle(HttpResponse.class);
    }

    @Override
    public Single<HttpResponse> doVideoListApiCall(String catalogId, String pnum) {
        return Rx2AndroidNetworking
                .get(ApiEndPoint.VIDEO_LIST)
                .addQueryParameter("catalogId",catalogId)
                .addQueryParameter("pnum",pnum)
                .build()
                .getObjectSingle(HttpResponse.class);
    }


    @Override
    public Single<HttpResponse> doSimpleGetAction(String url) {
        return Rx2AndroidNetworking
                .get(url)
                .build()
                .getObjectSingle(HttpResponse.class);
    }

}
