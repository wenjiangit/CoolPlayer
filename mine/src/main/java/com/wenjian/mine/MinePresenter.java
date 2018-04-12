package com.wenjian.mine;


import com.wenjian.base.ui.AppPresenter;

/**
 * Description: MinePresenter
 * Date: 2018/2/7
 *
 * @author jian.wen@ubtrobot.com
 */

public class MinePresenter extends AppPresenter<MineContract.View>
        implements MineContract.Presenter {
    @Override
    public void getDisplayRecords() {
        /*getDataManager().getRecordDataSource().getDisplayRecords(new DataSource.LoadCallback<Record>() {
            @Override
            public void onDataLoaded(List<Record> dataList) {
                if (!isActive()) {
                    return;
                }
                getView().onDisplayLoaded(dataList);
            }

            @Override
            public void onDataNotAvailable() {
            }
        });*/
    }

    @Override
    public void saveUserHead(String head) {
//        getDataManager().saveUserHead(head);
    }

    @Override
    public String getUserHead() {
        return null;
    }

}
