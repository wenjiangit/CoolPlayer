package com.wenjian.myplayer.ui.finder;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.data.network.model.VideoDetail;
import com.wenjian.myplayer.ui.base.AppBaseView;

import java.util.List;

/**
 * Description: FinderContract
 * Date: 2018/1/30
 *
 * @author jian.wen@ubtrobot.com
 */

public class FinderContract {

    public interface View extends AppBaseView{

        void onLoadSuccess(List<VideoDetail> details);
    }

    public interface Presenter extends MvpPresenter<View>{

        void loadData();

    }
}