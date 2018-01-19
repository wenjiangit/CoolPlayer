package com.wenjian.myplayer.data.network;

import com.wenjian.myplayer.data.network.model.HttpResponse;

import io.reactivex.Single;

/**
 * Description: ApiHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public interface ApiHelper {


    /**
     * 获取首页数据
     *
     * @return HttpResponse
     */
    Single<HttpResponse> doHomePagerApiCall();


    /**
     * 获取影片详情数据
     *
     * @param url 请求链接
     * @return HttpResponse
     */
    Single<HttpResponse> doVideoInfoApiCall(String url);


    /**
     * 获取影片评论
     *
     * @param mediaId 影片id
     * @param pnum    页数
     * @return HttpResponse
     */
    Single<HttpResponse> doCommentListApiCall(String mediaId, String pnum);


    /**
     * 获取影片列表
     *
     * @param catagoryId 分类id
     * @param pnum       页数
     * @return
     */
    Single<HttpResponse> doVideoListApiCall(String catagoryId, String pnum);

}
