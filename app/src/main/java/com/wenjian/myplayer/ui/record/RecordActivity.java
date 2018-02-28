package com.wenjian.myplayer.ui.record;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenjian.core.widget.recycler.DefaultItemDecoration;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.db.source.record.Record;
import com.wenjian.myplayer.di.Injector;
import com.wenjian.myplayer.ui.base.AppBaseActivity;
import com.wenjian.myplayer.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 历史记录`
 * Date: 2018/1/26
 *
 * @author jian.wen@ubtrobot.com
 */

public class RecordActivity extends AppBaseActivity<RecordContract.View, RecordContract.Presenter>
        implements RecordContract.View {

    @BindView(R.id.title_bar)
    CommonTitleBar mTitleBar;
    @BindView(R.id.record_recycler)
    RecyclerView mRecordRecycler;
    @BindView(R.id.lay_edit)
    LinearLayout mLayEdit;
    @BindView(R.id.tv_chose_all)
    TextView mTvChoseAll;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;

    private RecordAdapter mRecordAdapter;

    public static final int STATE_NORMAL = 1;
    public static final int STATE_EDIT = 2;
    private int mCurrentState = STATE_NORMAL;

    private boolean isChoseAll = true;

    public static void start(Context context) {
        Intent starter = new Intent(context, RecordActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mTitleBar.setTitle("历史记录");
        mTitleBar.addRightMenu("编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRightMenuClick();
            }
        });
        setupRecycler();
    }

    private void handleRightMenuClick() {
        if (mCurrentState == STATE_NORMAL) {
            mCurrentState = STATE_EDIT;
            mLayEdit.setVisibility(View.VISIBLE);
            mTitleBar.getRightMenu().setText("取消");

        } else {
            mCurrentState = STATE_NORMAL;
            mLayEdit.setVisibility(View.GONE);
            mTitleBar.getRightMenu().setText("编辑");
        }
        mRecordAdapter.changeUiState(mCurrentState);
    }

    private void setupRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecordRecycler.setLayoutManager(layoutManager);
        mRecordRecycler.addItemDecoration(new DefaultItemDecoration(this));
        mRecordAdapter = new RecordAdapter();
        mRecordRecycler.setAdapter(mRecordAdapter);
    }

    @OnClick(R.id.tv_chose_all)
    void choseAll() {
        mTvChoseAll.setText(isChoseAll ? "取消全选" : "全选");
        mRecordAdapter.choseAllOrNot(isChoseAll);
        isChoseAll = !isChoseAll;
    }

    @OnClick(R.id.tv_delete)
    void delete() {
        List<String> selectedIds = mRecordAdapter.getSelectedIds();
        if (selectedIds.isEmpty()) {
            return;
        }
        mRecordAdapter.delete();
        mCurrentState = STATE_NORMAL;
        mLayEdit.setVisibility(View.GONE);
        getPresenter().deleteRecords(selectedIds);
    }

    @Override
    protected void initData() {
        super.initData();
        getPresenter().loadData();
    }

    @Override
    public RecordContract.Presenter createPresenter() {
        return new RecordPresenter(Injector.provideRecordDataSource(this));
    }

    @Override
    public RecordContract.View createView() {
        return this;
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_record;
    }

    @Override
    public void onLoadSuccess(List<Record> records) {
        mRecordAdapter.setNewData(records);
    }

    @Override
    public void setEditEnable(boolean enable) {
        mTitleBar.getRightMenu().setEnabled(enable);
        if (enable) {
            mTitleBar.getRightMenu().setTextColor(Color.WHITE);
        } else {
            mTitleBar.getRightMenu().setTextColor(Color.parseColor("#ccc"));
        }
    }

}
