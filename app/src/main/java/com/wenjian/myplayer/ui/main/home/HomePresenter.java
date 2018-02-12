package com.wenjian.myplayer.ui.main.home;

import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.network.HttpEngine;
import com.wenjian.myplayer.entity.ApiResponse;
import com.wenjian.myplayer.entity.HomeRsp;
import com.wenjian.myplayer.ui.base.AppPresenter;

import io.reactivex.disposables.Disposable;
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
        Disposable homeDisposable = HttpEngine.getInstance()
                .service()
                .homePage()
                .subscribe(new Consumer<ApiResponse<HomeRsp>>() {
                    @Override
                    public void accept(ApiResponse<HomeRsp> response) throws Exception {
                        getView().hideLoading();
                        if (response.isSuccess()) {
                            HomeRsp result = response.getRet();
                            getView().onLoadSuccess(result.getList());
                            Logger.d(TAG, "result: %s", result);
                        } else {
                            handleApiError(response);
                        }
                    }
                }, providerExHandler());

        addDisposable(homeDisposable);
    }

}
