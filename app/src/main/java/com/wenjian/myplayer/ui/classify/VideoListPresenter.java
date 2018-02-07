package com.wenjian.myplayer.ui.classify;

import com.google.gson.Gson;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.db.source.DataSource;
import com.wenjian.myplayer.data.db.source.collection.Collection;
import com.wenjian.myplayer.data.network.ApiEndPoint;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.data.network.model.VideoListInfo;
import com.wenjian.myplayer.ui.base.AppPresenter;
import com.wenjian.myplayer.utils.FileUtils;

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

        addDisposable(getDataManager().doSimpleGetAction(ApiEndPoint.POPU_MSG)
                .subscribeOn(getSchedulerProvider().io())
                .doAfterSuccess(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        String json = new Gson().toJson(response);
                        FileUtils.save("POPU_MSG", json);
                    }
                })
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {

                    }
                }));


        addDisposable(getDataManager().doSimpleGetAction(ApiEndPoint.HOT_SEARCH)
                .subscribeOn(getSchedulerProvider().io())
                .doAfterSuccess(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        String json = new Gson().toJson(response);
                        FileUtils.save("HOT_SEARCH", json);
                    }
                })
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {

                    }
                }));


        addDisposable(getDataManager().doSimpleGetAction(ApiEndPoint.FIND_MOVIE_PAGE)
                .subscribeOn(getSchedulerProvider().io())
                .doAfterSuccess(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        String json = new Gson().toJson(response);
                        FileUtils.save("FIND_MOVIE_PAGE", json);
                    }
                })
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {

                    }
                }));


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
