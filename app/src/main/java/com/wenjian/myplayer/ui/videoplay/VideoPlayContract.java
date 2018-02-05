package com.wenjian.myplayer.ui.videoplay;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.data.db.source.collection.Collection;
import com.wenjian.myplayer.data.db.source.record.Record;
import com.wenjian.myplayer.data.network.model.Comment;
import com.wenjian.myplayer.data.network.model.VideoInfo;
import com.wenjian.myplayer.ui.base.AppView;

import java.util.List;

/**
 * Description: VideoPlayContract
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoPlayContract {

    public interface View extends AppView {

        /**
         * 加载成功
         *
         * @param info VideoInfo
         */
        void onLoadSuccess(VideoInfo info);


        /**
         * 影片描述
         *
         * @param videoDesc 影片介绍
         */
        void onVideoDescBuild(String videoDesc);


        /**
         * 评论加载成功
         *
         * @param comments 评论列表
         */
        void onCommentLoaded(List<Comment> comments);


        /**
         * 没有更多了
         */
        void setLoadMoreEnd();


        /**
         * 设置评论个数
         *
         * @param count 条数
         */
        void setCommentCount(int count);

    }

    public interface Presenter extends MvpPresenter<View> {

        /**
         * 加载影片数据
         *
         * @param mediaId Url
         */
        void loadVideoInfo(String mediaId);


        /**
         * 拉取评论
         *
         * @param mediaId    id
         * @param isLoadMore 是否是加载更多
         */
        void getCommentList(String mediaId, boolean isLoadMore);


        /**
         * 保存当前视频的播放状态
         *
         * @param record 播放记录
         */
        void saveRecord(Record record);


        /**
         * 添加到收藏
         *
         * @param collection  Collection
         */
        void addCollection(Collection collection);


    }

}
