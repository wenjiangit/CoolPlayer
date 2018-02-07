package com.wenjian.myplayer.ui.mine;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.db.source.record.Record;
import com.wenjian.myplayer.ui.base.AppBaseFragment;
import com.wenjian.myplayer.ui.classify.VideoListActivity;
import com.wenjian.myplayer.ui.record.RecordActivity;
import com.wenjian.myplayer.ui.videoplay.VideoPlayActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * 我的
 *
 * @author wenjian
 */
public class MineFragment extends AppBaseFragment<MineContract.View, MineContract.Presenter>
        implements MineContract.View {


    @BindView(R.id.iv_head)
    CircleImageView mIvHead;
    @BindView(R.id.history_recycler)
    RecyclerView mHistoryRecycler;

    BaseQuickAdapter<Record, BaseViewHolder> mAdapter;
    @BindView(R.id.lay_container)
    LinearLayout mLayContainer;
    @BindView(R.id.lay_collect)
    RelativeLayout mLayCollect;


    @BindView(R.id.iv_bg)
    ImageView mbg;

    private static final int REQUEST_CODE_CHOOSE = 100;

    public MineFragment() {
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);
        setupRecycler();

        mLayCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoListActivity.start(getActivity(), null, VideoListActivity.TITLE_COLLECTION);
            }
        });

    }

    private void setupRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mHistoryRecycler.setLayoutManager(manager);
        mAdapter = new BaseQuickAdapter<Record, BaseViewHolder>(R.layout.cell_history) {
            @Override
            protected void convert(BaseViewHolder helper, final Record item) {
                helper.setText(R.id.tv_title, item.getTitle());
                ImageView ivThumb = helper.getView(R.id.iv_thumb);
                Glide.with(MineFragment.this)
                        .load(item.getThumb())
                        .into(ivThumb);
                double percent = ((double) item.getCurrentProgress()) / item.getTotalProgress() * 100;
                helper.setText(R.id.tv_progress, Math.round(percent) + "%");
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VideoPlayActivity.start(getContext(), item.getId());
                    }
                });
            }
        };
        mHistoryRecycler.setAdapter(mAdapter);
    }


    @OnClick({R.id.lay_history, R.id.iv_head})
    void onclickView(View view) {
        switch (view.getId()) {
            case R.id.lay_history:
                RecordActivity.start(getActivity());
                break;

            case R.id.iv_head:
                Matisse.from(this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(9)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
                break;
            default:
        }
    }

    @Override
    protected void initData() {
        super.initData();
        getPresenter().getDisplayRecords();
        String userHead = getPresenter().getUserHead();
        mIvHead.setImageURI(Uri.parse(userHead));
    }

    @Override
    public void onDisplayLoaded(List<Record> recordList) {
        mAdapter.setNewData(recordList);
    }

    @Override
    public MineContract.Presenter createPresenter() {
        return new MinePresenter();
    }

    @Override
    public MineContract.View createView() {
        return this;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
            Uri uri = mSelected.get(0);
            getPresenter().saveUserHead(uri.toString());
            mIvHead.setImageURI(uri);
            if (mSelected.size() > 1) {
                mbg.setImageURI(mSelected.get(1));
            }
        }
    }
}
