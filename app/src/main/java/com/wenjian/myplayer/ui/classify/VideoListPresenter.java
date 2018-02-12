package com.wenjian.myplayer.ui.classify;

import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.db.source.DataSource;
import com.wenjian.myplayer.data.db.source.collection.Collection;
import com.wenjian.myplayer.data.network.HttpEngine;
import com.wenjian.myplayer.entity.ApiResponse;
import com.wenjian.myplayer.entity.VideoListInfo;
import com.wenjian.myplayer.ui.base.AppPresenter;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description: VideoListPresenter
 * Date: 2018/1/18
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoListPresenter extends AppPresenter<VideoListContract.View>
        implements VideoListContract.Presenter {

    private int mPagerNum = 1;

    @Override
    public void getVideoList(String catalogId, final boolean isLoadMore) {
        if (!isLoadMore) {
            getView().showLoading();
        }
        mPagerNum = isLoadMore ? mPagerNum + 1 : 1;

        HttpEngine.getInstance()
                .service()
                .getVideoList(catalogId, String.valueOf(mPagerNum))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<ApiResponse<VideoListInfo>>() {
                    @Override
                    public void accept(ApiResponse<VideoListInfo> response) throws Exception {
                        getView().hideLoading();
                        if (response.isSuccess()) {
                            VideoListInfo videoListInfo = response.getRet();
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
                }, providerExHandler());
    }

    @Override
    public void loadAllCollections() {
        getView().showLoading();
        getDataManager().getCollectionDataSource().loadAllAsync(new DataSource.LoadCallback<Collection>() {
            @Override
            public void onDataLoaded(List<Collection> dataList) {
                if (!isActive()) {
                    return;
                }
                getView().hideLoading();
                getView().onCollectionLoaded(dataList);
            }

            @Override
            public void onDataNotAvailable() {
                if (!isActive()) {
                    return;
                }
                getView().hideLoading();
                getView().showMessage("没有数据!");
            }
        });

    }
}
