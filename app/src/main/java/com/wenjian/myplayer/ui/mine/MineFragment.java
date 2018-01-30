package com.wenjian.myplayer.ui.mine;


import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wenjian.core.ui.base.BaseFragment;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.db.AppDbHelper;
import com.wenjian.myplayer.data.db.model.Collection;
import com.wenjian.myplayer.ui.record.RecordActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 *
 * @author wenjian
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.btn_record)
    Button mBtnRecord;
    @BindView(R.id.btn_collect)
    Button mBtnCollect;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);

    }

    @OnClick(R.id.btn_record)
    void record() {
        RecordActivity.start(getActivity());
    }

     @OnClick(R.id.btn_collect)
    void collect() {

    }

    @Override
    protected void initData() {
        super.initData();
        List<Collection> records = AppDbHelper.getInstance().loadAllSync(Collection.class);
        Log.d(TAG, "initData: " + records);
    }


}
