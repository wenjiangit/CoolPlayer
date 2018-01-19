package com.wenjian.myplayer.ui.classify;

import com.google.gson.Gson;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.data.network.model.VideoListInfo;
import com.wenjian.myplayer.ui.base.AppBasePresenter;
import com.wenjian.myplayer.utils.FileUtils;

import io.reactivex.functions.Consumer;

/**
 * Description: VideoListPresenter
 * Date: 2018/1/18
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoListPresenter extends AppBasePresenter<VideoListContract.View>
        implements VideoListContract.Presenter {

    private int mPagerNum = 1;

    @Override
    public void getVideoList(String catalogId, final boolean isLoadMore) {
        if (!isLoadMore) {
            getView().showLoading();
        }
        mPagerNum = isLoadMore ? mPagerNum + 1 : 1;
        addDisposable(getDataManager().doVideoListApiCall(catalogId, String.valueOf(mPagerNum))
                .subscribeOn(getSchedulerProvider().io())
                .doAfterSuccess(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        String json = new Gson().toJson(response);
                        FileUtils.save("doVideoListApiCall", json);
                    }
                })
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        getView().hideLoading();
                        if (response.isSuccess()) {
                            VideoListInfo videoListInfo = response.getResult(VideoListInfo.class);
                            Logger.d(TAG, "videoListInfo: %s", videoListInfo);
                            if (isLoadMore) {
                                if (mPagerNum <= videoListInfo.getTotalPnum()) {
                                    getView().onLoadMoreComplete();
                                } else {
                                    getView().onLoadMoreEnd();
                                }
                            }
                            getView().onDataLoaded(videoListInfo);
                        } else {
                            handleApiError(response);
                        }
                    }
                }, getThrowableConsumer()));
    }
}
