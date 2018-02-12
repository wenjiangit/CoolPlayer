package com.wenjian.myplayer.ui.base;

import com.wenjian.core.mvp.lifemvp.LifeMvpPresenter;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.AppDataManager;
import com.wenjian.myplayer.data.DataManager;
import com.wenjian.myplayer.entity.ApiResponse;
import com.wenjian.myplayer.rx.AppSchedulerProvider;
import com.wenjian.myplayer.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Description: AppPresenter
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppPresenter<V extends AppView> extends LifeMvpPresenter<V> {

    protected final String TAG = this.getClass().getSimpleName();

    private final DataManager mDataManager;
    private final CompositeDisposable mCompositeDisposable;

    private Consumer<Throwable> mThrowableConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            getView().hideLoading();
            Logger.e(TAG, "throwable : %s", throwable.getMessage());
            getView().onError(R.string.network_error);
        }
    };

    public AppPresenter() {
        mDataManager = AppDataManager.getInstance();
        mCompositeDisposable = new CompositeDisposable();
    }

    protected DataManager getDataManager() {
        return mDataManager;
    }

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected Consumer<Throwable> providerExHandler() {
        return mThrowableConsumer;
    }

    protected void handleApiError(ApiResponse response) {
        if (!response.isSuccess()) {
            getView().onError(response.getMsg());
        }
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
