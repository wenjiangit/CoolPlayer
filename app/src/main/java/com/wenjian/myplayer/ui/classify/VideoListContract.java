package com.wenjian.myplayer.ui.classify;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.data.network.model.VideoListInfo;
import com.wenjian.myplayer.ui.base.AppBaseView;

/**
 * Description: VideoListContract
 * Date: 2018/1/18
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoListContract {

    public interface View extends AppBaseView{

        void onDataLoaded(VideoListInfo info);


        void onLoadMoreEnd();

        void onLoadMoreComplete();

    }


    public interface Presenter extends MvpPresenter<View>{

        void getVideoList(String catalogId,boolean isLoadMore);

    }
}
