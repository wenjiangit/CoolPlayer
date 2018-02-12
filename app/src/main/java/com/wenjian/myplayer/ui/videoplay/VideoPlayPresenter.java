package com.wenjian.myplayer.ui.videoplay;

import android.text.TextUtils;

import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.db.source.collection.Collection;
import com.wenjian.myplayer.data.db.source.record.Record;
import com.wenjian.myplayer.data.network.HttpEngine;
import com.wenjian.myplayer.entity.ApiResponse;
import com.wenjian.myplayer.entity.Comment;
import com.wenjian.myplayer.entity.CommentInfo;
import com.wenjian.myplayer.entity.VideoInfo;
import com.wenjian.myplayer.ui.base.AppPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Description: VideoPlayPresenter
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoPlayPresenter extends AppPresenter<VideoPlayContract.View>
        implements VideoPlayContract.Presenter {

    private int mPageNum = 1;

    @Override
    public void loadVideoInfo(String mediaId) {
        if (TextUtils.isEmpty(mediaId)) {
            getView().showMessage("mediaId is empty ...");
            return;
        }
        getView().showLoading();

        Disposable videoInfoDisposable = HttpEngine.getInstance()
                .service()
                .videoDetail(mediaId)
                .subscribe(new Consumer<ApiResponse<VideoInfo>>() {
                    @Override
                    public void accept(ApiResponse<VideoInfo> apiResponse) throws Exception {
                        getView().hideLoading();
                        Logger.d(TAG, "loadVideoInfo >>> %s", apiResponse);
                        if (apiResponse.isSuccess()) {
                            VideoInfo videoInfo = apiResponse.getRet();
                            getView().onLoadSuccess(videoInfo);
                            buildVideoDesc(videoInfo);
                        } else {
                            handleApiError(apiResponse);
                        }
                    }
                }, providerExHandler());

        addDisposable(videoInfoDisposable);
    }

    @Override
    public void getCommentList(String mediaId, final boolean isLoadMore) {
        if (TextUtils.isEmpty(mediaId)) {
            getView().showMessage("mediaId is empty ...");
            return;
        }
        if (isLoadMore) {
            mPageNum++;
        } else {
            mPageNum = 1;
        }

        Disposable disposable = HttpEngine.getInstance()
                .service()
                .getCommentList(mediaId, String.valueOf(mPageNum))
                .subscribe(new Consumer<ApiResponse<CommentInfo>>() {
                    @Override
                    public void accept(ApiResponse<CommentInfo> apiResponse) throws Exception {
                        if (apiResponse.isSuccess()) {
                            final CommentInfo commentInfo = apiResponse.getRet();
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
                            handleApiError(apiResponse);
                        }
                    }
                }, providerExHandler());
        addDisposable(disposable);
    }

    @Override
    public void saveRecord(Record record) {
        getDataManager().getRecordDataSource().saveSingle(record);
    }

    @Override
    public void addCollection(Collection collection) {
        getDataManager().getCollectionDataSource().saveSingle(collection);
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
