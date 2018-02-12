package com.wenjian.myplayer.data.network;

import com.wenjian.myplayer.entity.ApiResponse;
import com.wenjian.myplayer.entity.CommentInfo;
import com.wenjian.myplayer.entity.HomeRsp;
import com.wenjian.myplayer.entity.VideoInfo;
import com.wenjian.myplayer.entity.VideoListInfo;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Description: MovieService
 * Date: 2018/2/12
 *
 * @author jian.wen@ubtrobot.com
 */

public interface MovieService {
    @GET(ApiEndPoint.HOME_PAGER)
    Flowable<ApiResponse<HomeRsp>> homePage();


    @GET(ApiEndPoint.VIDEO_DETAIL)
    Flowable<ApiResponse<VideoInfo>> videoDetail(@Query("mediaId") String mediaId);


    @GET(ApiEndPoint.VIDEO_COMMENT)
    Flowable<ApiResponse<CommentInfo>> getCommentList(@Query("mediaId") String mediaId, @Query("pnum") String pnum);


    @GET(ApiEndPoint.VIDEO_LIST)
    Flowable<ApiResponse<VideoListInfo>> getVideoList(@Query("catalogId") String catalogId, @Query("pnum") String pnum);

}
