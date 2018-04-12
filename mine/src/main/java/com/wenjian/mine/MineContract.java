package com.wenjian.mine;



import com.wenjian.base.data.db.source.record.Record;
import com.wenjian.base.mvp.base.MvpPresenter;
import com.wenjian.base.ui.AppView;

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
