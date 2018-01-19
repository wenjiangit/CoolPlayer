package com.wenjian.myplayer.ui.base;

import com.wenjian.core.mvp.base.BaseMvpPresenter;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.AppDataManager;
import com.wenjian.myplayer.data.DataManager;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.rx.AppSchedulerProvider;
import com.wenjian.myplayer.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Description: AppBasePresenter
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppBasePresenter<V extends AppBaseView> extends BaseMvpPresenter<V> {

    protected final String TAG = this.getClass().getSimpleName();

    private final DataManager mDataManager;
    private final CompositeDisposable mCompositeDisposable;
    private final SchedulerProvider mSchedulerProvider;

    private Consumer<Throwable> mThrowableConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            getView().hideLoading();
            Logger.e(TAG,"throwable : %s",throwable.getMessage());
            getView().onError(R.string.network_error);
        }
    };

    public AppBasePresenter() {
        mDataManager = AppDataManager.getInstance();
        mCompositeDisposable = new CompositeDisposable();
        mSchedulerProvider = new AppSchedulerProvider();
    }


    protected DataManager getDataManager() {
        return mDataManager;
    }

    protected SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected Consumer<Throwable> getThrowableConsumer() {
        return mThrowableConsumer;
    }

    protected void handleApiError(HttpResponse response) {
        if (!response.isSuccess()) {
            getView().onError(response.getMsg());
        }
    }


    @Override
    public void detachView() {
        super.detachView();
        mCompositeDisposable.dispose();
    }

}
