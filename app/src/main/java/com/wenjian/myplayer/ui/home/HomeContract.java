package com.wenjian.myplayer.ui.home;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.data.network.model.ShowDetail;
import com.wenjian.myplayer.ui.base.AppBaseView;

import java.util.List;

/**
 * Description: HomeContract
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class HomeContract {

    public interface View extends AppBaseView {

        /**
         * 数据加载成功
         *
         * @param list 列表数据
         */
        void onLoadSuccess(List<ShowDetail> list);

    }

    public interface Presenter extends MvpPresenter<View> {
        /**
         * 开始加载
         */
        void start();
    }


}
