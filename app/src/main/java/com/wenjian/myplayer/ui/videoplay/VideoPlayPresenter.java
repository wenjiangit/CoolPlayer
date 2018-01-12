package com.wenjian.myplayer.ui.videoplay;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.network.model.CommentInfo;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.data.network.model.VideoInfo;
import com.wenjian.myplayer.ui.base.AppBasePresenter;
import com.wenjian.myplayer.utils.FileUtils;

import io.reactivex.functions.Consumer;

/**
 * Description: VideoPlayPresenter
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoPlayPresenter extends AppBasePresenter<VideoPlayContract.View>
        implements VideoPlayContract.Presenter {

    private int pageNum = 1;

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
    public void getCommentList(String mediaId) {
        addDisposable(getDataManager()
                .doCommentListApiCall(mediaId, String.valueOf(pageNum))
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
                            CommentInfo commentInfo = response.getResult(CommentInfo.class);
                            getView().onCommentLoaded(commentInfo.getCommentList());
                        } else {
                            handleApiError(response);
                        }
                    }
                }, getThrowableConsumer()));
    }

    private void buildVideoDesc(VideoInfo result) {
        StringBuilder videoDesc = new StringBuilder();
        videoDesc.append(result.getAirTime()).append(" | ")
                .append(result.getRegion()).append(" | ")
                .append(result.getVideoType()).append("\n")
                .append("主演: ")
                .append(result.getActors()).append("\n")
                .append("导演: ")
                .append(result.getDirector()).append("\n")
                .append("时长: ")
                .append(result.getDuration()).append("\n")
                .append("剧情简介: ").append("\n")
                .append(result.getDescription());
        getView().onVideoDescBuild(videoDesc.toString());
    }
}
