package com.wenjian.myplayer.data.network;

import com.wenjian.myplayer.entity.ApiResponse;
import com.wenjian.myplayer.entity.CommentInfo;
import com.wenjian.myplayer.entity.HomeRsp;
import com.wenjian.myplayer.entity.VideoInfo;
import com.wenjian.myplayer.entity.VideoListInfo;
import com.wenjian.myplayer.rx.AppSchedulerProvider;
import com.wenjian.myplayer.rx.SchedulerProvider;

import io.reactivex.Flowable;

/**
 * Description: MovieServiceImpl
 * Date: 2018/2/12
 *
 * @author jian.wen@ubtrobot.com
 */

public class MovieServiceImpl implements MovieService {

    private MovieService mService;
    private SchedulerProvider mSchedulerProvider;

    MovieServiceImpl(MovieService service, SchedulerProvider schedulerProvider) {
        mService = service;
        mSchedulerProvider = schedulerProvider;
    }

    MovieServiceImpl(MovieService service) {
        this(service, new AppSchedulerProvider());
    }

    @Override
    public Flowable<ApiResponse<HomeRsp>> homePage() {
        return mService.homePage()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.mainThread());
    }

    @Override
    public Flowable<ApiResponse<VideoInfo>> videoDetail(String mediaId) {
        return mService.videoDetail(mediaId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.mainThread());
    }

    @Override
    public Flowable<ApiResponse<CommentInfo>> getCommentList(String mediaId, String pnum) {
        return mService.getCommentList(mediaId, pnum)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.mainThread());
    }

    @Override
    public Flowable<ApiResponse<VideoListInfo>> getVideoList(String catalogId, String pnum) {
        return mService.getVideoList(catalogId, pnum)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.mainThread());
    }
}
