package com.wenjian.myplayer.ui.videoplay;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.data.network.model.Comment;
import com.wenjian.myplayer.data.network.model.VideoInfo;
import com.wenjian.myplayer.ui.base.AppBaseView;

import java.util.List;

/**
 * Description: VideoPlayContract
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class VideoPlayContract {

    public interface View extends AppBaseView {

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
         * @param count 条数
         */
        void setCommentCount(int count);

    }

    public interface Presenter extends MvpPresenter<View> {

        /**
         * 加载影片数据
         *
         * @param loadUrl Url
         */
        void loadVideoInfo(String loadUrl);


        /**
         * 拉取评论
         *
         * @param mediaId    id
         * @param isLoadMore 是否是加载更多
         */
        void getCommentList(String mediaId, boolean isLoadMore);


    }

}
