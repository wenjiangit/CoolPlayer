package com.wenjian.myplayer.ui.home;

import android.util.Log;

import com.google.gson.Gson;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.network.model.HomeRsp;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.ui.base.AppPresenter;
import com.wenjian.myplayer.utils.FileUtils;

import io.reactivex.functions.Consumer;


/**
 * Description: HomePresenter
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class HomePresenter extends AppPresenter<HomeContract.View>
        implements HomeContract.Presenter {

    @Override
    public void start() {
        getView().showLoading();

        addDisposable(getDataManager().doHomePagerApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .doAfterSuccess(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        String json = new Gson().toJson(response);
                        FileUtils.save("doHomePagerApiCall", json);
                    }
                })
                .doOnSuccess(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        Log.d(TAG, "doOnSuccess: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        getView().hideLoading();
                        if (response.isSuccess()) {
                            HomeRsp result = response.getResult(HomeRsp.class);
                            getView().onLoadSuccess(result.getList());
                            Logger.d(TAG, "result: %s", result);
                        } else {
                            handleApiError(response);
                        }
                    }
                }, getThrowableConsumer()));
    }



   /* private void saveDataToDb(HttpResponse response) {
        if (response.isSuccess()) {
            HomeRsp result = response.getResult(HomeRsp.class);
            List<ShowDetail> list = result.getList();
            if (list != null && !list.isEmpty()) {
                getDataManager().saveShowDetail(list.toArray(new ShowDetail[0]));
                for (ShowDetail detail : list) {
                    List<VideoDetail> childList = detail.getChildList();
                    if (childList != null && !childList.isEmpty()) {
                        getDataManager().saveVideos(childList.toArray(new VideoDetail[0]));
                    }
                }
            }
        }
    }*/
}
