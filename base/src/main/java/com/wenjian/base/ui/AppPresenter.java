package com.wenjian.base.ui;

import com.wenjian.base.R;
import com.wenjian.base.entity.ApiResponse;
import com.wenjian.base.mvp.lifemvp.LifeMvpPresenter;
import com.wenjian.base.utils.Logger;


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

    private CompositeDisposable mCompositeDisposable;

    private Consumer<Throwable> mThrowableConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            getView().hideLoading();
            Logger.e(TAG, "throwable : %s", throwable.getMessage());
            getView().onError(R.string.network_error);
        }
    };

    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
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
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
