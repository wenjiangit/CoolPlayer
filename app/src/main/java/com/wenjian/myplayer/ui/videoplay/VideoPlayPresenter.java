package com.wenjian.myplayer.ui.videoplay;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.network.model.Comment;
import com.wenjian.myplayer.data.network.model.CommentInfo;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.data.network.model.VideoInfo;
import com.wenjian.myplayer.ui.base.AppBasePresenter;
import com.wenjian.myplayer.utils.FileUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description: VideoPlayPresenter
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoPlayPresenter extends AppBasePresenter<VideoPlayContract.View>
        implements VideoPlayContract.Presenter {

    private int mPageNum = 1;

    @Override
    public void loadVideoInfo(String loadUrl) {
        if (TextUtils.isEmpty(loadUrl)) {
            getView().showMessage("loadUrl is empty ...");
            return;
        }
        getView().showLoading();
        addDisposable(getDataManager()
                .doVideoInfoApiCall(loadUrl)
                .doAfterSuccess(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        Log.d(TAG, "thread: " + Thread.currentThread().getName());
                        String json = new Gson().toJson(response);
                        FileUtils.save("doVideoInfoApiCall", json);
                    }
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        getView().hideLoading();
                        if (response.isSuccess()) {
                            Logger.d(TAG, "loadVideoInfo >>> %s", response);
                            final VideoInfo result = response.getResult(VideoInfo.class);
                            getView().onLoadSuccess(result);
                            buildVideoDesc(result);
                        } else {
                            handleApiError(response);
                        }
                    }
                }, getThrowableConsumer()));
    }

    @Override
    public void getCommentList(String mediaId, final boolean isLoadMore) {
        if (isLoadMore) {
            mPageNum++;
        } else {
            mPageNum = 1;
        }
        addDisposable(getDataManager()
                .doCommentListApiCall(mediaId, String.valueOf(mPageNum))
                .doAfterSuccess(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        String json = new Gson().toJson(response);
                        FileUtils.save("doCommentListApiCall", json);
                    }
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        if (response.isSuccess()) {
                            final CommentInfo commentInfo = response.getResult(CommentInfo.class);
                            final List<Comment> commentList = commentInfo.getCommentList();
                            final int totalRecords = commentInfo.getTotalRecords();
                            getView().setCommentCount(totalRecords);
                            if (isLoadMore) {
                                if (mPageNum <= commentInfo.getTotalPnum()) {
                                    getView().onCommentLoaded(commentList);
                                } else {
                                    getView().setLoadMoreEnd();
                                }
                            } else {
                                getView().onCommentLoaded(commentList);
                            }
                        } else {
                            handleApiError(response);
                        }
                    }
                }, getThrowableConsumer()));
    }

    private void buildVideoDesc(VideoInfo result) {
        final String videoType = result.getVideoType();
        final int airTime = result.getAirTime();
        final String director = result.getDirector();
        final String actors = result.getActors();
        final String duration = result.getDuration();
        final String description = result.getDescription();
        final String region = result.getRegion();

        StringBuilder videoDescBuilder = new StringBuilder();
        if (airTime != 0) {
            videoDescBuilder.append(airTime);
        }
        if (!TextUtils.isEmpty(region)) {
            videoDescBuilder.append(" | ")
                    .append(region);
        }
        if (TextUtils.isEmpty(videoType)) {
            videoDescBuilder.append(videoType);
        }
        videoDescBuilder.append("\n");
        if (!TextUtils.isEmpty(actors)) {
            videoDescBuilder.append("主演: ").append(actors).append("\n");
        }
        if (!TextUtils.isEmpty(director)) {
            videoDescBuilder.append("导演: ").append(director).append("\n");
        }
        if (!TextUtils.isEmpty(duration)) {
            videoDescBuilder.append("时长: ").append(duration).append("\n");
        }
        if (!TextUtils.isEmpty(description)) {
            videoDescBuilder.append("剧情简介: ").append("\n").append(description);
        }
        getView().onVideoDescBuild(videoDescBuilder.toString());
    }
}
