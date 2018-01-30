package com.wenjian.myplayer.ui.videoplay;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.db.AppDbHelper;
import com.wenjian.myplayer.data.db.model.Collection;
import com.wenjian.myplayer.data.db.model.Record;
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
    public void loadVideoInfo(String mediaId) {
        if (TextUtils.isEmpty(mediaId)) {
            getView().showMessage("mediaId is empty ...");
            return;
        }
        getView().showLoading();
        addDisposable(getDataManager()
                .doVideoDetailApiCall(mediaId)
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

    @Override
    public void saveRecord(Record record) {
        AppDbHelper.getInstance().save(Record.class, record);
    }

    @Override
    public void addCollection(Collection collection) {
        AppDbHelper.getInstance().save(Collection.class, collection);
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
        boolean hasLine = false;
        if (airTime != 0) {
            videoDescBuilder.append(airTime);
            hasLine = true;
        }
        if (!TextUtils.isEmpty(region)) {
            videoDescBuilder.append(" | ")
                    .append(region);
            hasLine = true;
        }
        if (!TextUtils.isEmpty(videoType)) {
            videoDescBuilder.append(videoType);
            hasLine = true;
        }
        if (hasLine) {
            videoDescBuilder.append("\n");
        }
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
