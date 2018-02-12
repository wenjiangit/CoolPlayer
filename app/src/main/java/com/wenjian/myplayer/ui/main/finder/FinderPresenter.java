package com.wenjian.myplayer.ui.main.finder;

import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.data.network.HttpEngine;
import com.wenjian.myplayer.entity.ApiResponse;
import com.wenjian.myplayer.entity.VideoListInfo;
import com.wenjian.myplayer.ui.base.AppPresenter;

import io.reactivex.disposables.Disposable;
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
        Disposable videoListDisposable = HttpEngine.getInstance()
                .service()
                .getVideoList(CATAGORY_ID, String.valueOf(mPagerNum++))
                .subscribe(new Consumer<ApiResponse<VideoListInfo>>() {
                    @Override
                    public void accept(ApiResponse<VideoListInfo> response) throws Exception {
                        getView().hideLoading();
                        if (response.isSuccess()) {
                            VideoListInfo videoListInfo = response.getRet();
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
                }, providerExHandler());

        addDisposable(videoListDisposable);

    }
}
