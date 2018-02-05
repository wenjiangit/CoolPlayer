package com.wenjian.myplayer.ui.finder;

import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.network.model.HttpResponse;
import com.wenjian.myplayer.data.network.model.VideoListInfo;
import com.wenjian.myplayer.ui.base.AppPresenter;

import io.reactivex.functions.Consumer;

/**
 * Description: FinderPresenter
 * Date: 2018/1/30
 *
 * @author jian.wen@ubtrobot.com
 */

public class FinderPresenter extends AppPresenter<FinderContract.View>
        implements FinderContract.Presenter {

    private int mPagerNum = 1;
    /**
     * 精彩推荐的分类id
     */
    private static final String CATAGORY_ID = "402834815584e463015584e53843000b";

    @Override
    public void loadData() {
        getView().showLoading();
        addDisposable(getDataManager().doVideoListApiCall(CATAGORY_ID, String.valueOf(mPagerNum++))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().mainThread())
                .subscribe(new Consumer<HttpResponse>() {
                    @Override
                    public void accept(HttpResponse response) throws Exception {
                        getView().hideLoading();
                        if (response.isSuccess()) {
                            VideoListInfo videoListInfo = response.getResult(VideoListInfo.class);
                            Logger.d(TAG, "videoListInfo: %s", videoListInfo);
                            if (mPagerNum <= videoListInfo.getTotalPnum()) {
                                getView().onLoadSuccess(videoListInfo.getList());
                            } else {
                                mPagerNum = 1;
                            }
                        } else {
                            handleApiError(response);
                        }
                    }
                }, getThrowableConsumer()));
    }
}
