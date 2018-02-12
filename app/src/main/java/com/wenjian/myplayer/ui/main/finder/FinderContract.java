package com.wenjian.myplayer.ui.main.finder;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.entity.VideoDetail;
import com.wenjian.myplayer.ui.base.AppView;

import java.util.List;

/**
 * Description: FinderContract
 * Date: 2018/1/30
 *
 * @author jian.wen@ubtrobot.com
 */

public class FinderContract {

    public interface View extends AppView {

        void onLoadSuccess(List<VideoDetail> details);
    }

    public interface Presenter extends MvpPresenter<View>{

        void loadData();

    }
}
