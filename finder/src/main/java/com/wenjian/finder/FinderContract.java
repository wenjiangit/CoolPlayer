package com.wenjian.finder;


import com.wenjian.base.entity.VideoDetail;
import com.wenjian.base.mvp.base.MvpPresenter;
import com.wenjian.base.ui.AppView;

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

    public interface Presenter extends MvpPresenter<View> {

        void loadData();

    }
}
