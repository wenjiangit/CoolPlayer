package com.wenjian.myplayer.ui.record;

import com.wenjian.core.mvp.base.MvpPresenter;
import com.wenjian.myplayer.data.db.source.record.Record;
import com.wenjian.myplayer.ui.base.AppBaseView;

import java.util.List;

/**
 * Description: RecordContract
 * Date: 2018/1/26
 *
 * @author jian.wen@ubtrobot.com
 */

public class RecordContract {

    public interface View extends AppBaseView{

        void onLoadSuccess(List<Record> records);

        void setEditEnable(boolean enable);

    }

    public interface Presenter extends MvpPresenter<View> {

        void loadData();

        void deleteRecords(List<String> recordIds);
    }

}
