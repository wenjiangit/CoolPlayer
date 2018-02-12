package com.wenjian.myplayer.ui.main.mine;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.data.db.source.record.Record;
import com.wenjian.myplayer.ui.base.AppView;

import java.util.List;

/**
 * Description: MineContract
 * Date: 2018/2/7
 *
 * @author jian.wen@ubtrobot.com
 */

public class MineContract {

    interface View extends AppView {

        void onDisplayLoaded(List<Record> recordList);

    }

    interface Presenter extends MvpPresenter<View> {

        void getDisplayRecords();

        void saveUserHead(String head);

        String getUserHead();
    }
}
