package com.wenjian.videoplay.list;


import com.wenjian.base.data.db.source.collection.Collection;
import com.wenjian.base.entity.VideoListInfo;
import com.wenjian.base.mvp.base.MvpPresenter;
import com.wenjian.base.ui.AppView;

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


    public interface Presenter extends MvpPresenter<View> {

        void getVideoList(String catalogId, boolean isLoadMore);

        void loadAllCollections();

    }
}
