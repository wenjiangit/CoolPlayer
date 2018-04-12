package com.wenjian.base.data.network;


import com.wenjian.base.entity.ApiResponse;
import com.wenjian.base.entity.CommentInfo;
import com.wenjian.base.entity.HomeRsp;
import com.wenjian.base.entity.VideoInfo;
import com.wenjian.base.entity.VideoListInfo;
import com.wenjian.base.utils.rx.AppSchedulerProvider;
import com.wenjian.base.utils.rx.SchedulerProvider;

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
