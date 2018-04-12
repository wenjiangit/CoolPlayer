package com.wenjian.videoplay.list;



import com.wenjian.base.data.db.source.collection.Collection;
import com.wenjian.base.data.db.source.collection.CollectionDataSource;
import com.wenjian.base.data.db.source.collection.LocalCollectionDataSource;
import com.wenjian.base.data.network.HttpEngine;
import com.wenjian.base.entity.ApiResponse;
import com.wenjian.base.entity.VideoListInfo;
import com.wenjian.base.ui.AppPresenter;
import com.wenjian.base.utils.Logger;
import com.wenjian.base.utils.rx.RxUtils;

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

    private CollectionDataSource mCollectionDataSource;

    VideoListPresenter() {
        mCollectionDataSource = LocalCollectionDataSource.getInstance();
    }

    @Override
    public void getVideoList(String catalogId, final boolean isLoadMore) {
        if (!isLoadMore) {
            getView().showLoading();
        }
        mPagerNum = isLoadMore ? mPagerNum + 1 : 1;

        HttpEngine.getInstance()
                .service()
                .getVideoList(catalogId, String.valueOf(mPagerNum))
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
        addDisposable(mCollectionDataSource.loadAll()
                .compose(RxUtils.<List<Collection>>transfor())
                .subscribe(new Consumer<List<Collection>>() {
                    @Override
                    public void accept(List<Collection> collections) throws Exception {
                        getView().hideLoading();
                        getView().onCollectionLoaded(collections);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().hideLoading();
                        getView().showMessage(throwable.getMessage());
                    }
                }));

    }
}
