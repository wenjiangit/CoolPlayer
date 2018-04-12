package com.wenjian.mine.record;


import com.wenjian.base.data.db.source.record.Record;
import com.wenjian.base.mvp.base.MvpPresenter;
import com.wenjian.base.ui.AppView;

import java.util.List;

/**
 * Description: RecordContract
 * Date: 2018/1/26
 *
 * @author jian.wen@ubtrobot.com
 */

public class RecordContract {

    public interface View extends AppView {

        void onLoadSuccess(List<Record> records);

        void setEditEnable(boolean enable);

    }

    public interface Presenter extends MvpPresenter<View> {

        void loadData();

        void deleteRecords(List<String> recordIds);
    }

}
