package com.wenjian.myplayer.ui.home;

import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.network.model.HomeRsp;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.ui.base.AppBasePresenter;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.functions.Consumer;


/**
 * Description: HomePresenter
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class HomePresenter extends AppBasePresenter<HomeContract.View>
        implements HomeContract.Presenter {

    @Override
    public void start() {
        getView().showLoading();

        addDisposable(getDataManager().doHomePagerApiCall()
                .subscribeOn(getSchedulerProvider().io())
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

}
