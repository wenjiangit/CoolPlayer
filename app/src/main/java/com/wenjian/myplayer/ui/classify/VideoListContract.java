package com.wenjian.myplayer.ui.classify;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.data.db.source.collection.Collection;
import com.wenjian.myplayer.entity.VideoListInfo;
import com.wenjian.myplayer.ui.base.AppView;

import java.util.List;

/**
 * Description: VideoListContract
 * Date: 2018/1/18
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoListContract {

    public interface View extends AppView {

        void onDataLoaded(VideoListInfo info);


        void onLoadMoreEnd();

        void onLoadMoreComplete();

        void onCollectionLoaded(List<Collection> collections);

    }


    public interface Presenter extends MvpPresenter<View>{

        void getVideoList(String catalogId,boolean isLoadMore);

        void loadAllCollections();

    }
}
