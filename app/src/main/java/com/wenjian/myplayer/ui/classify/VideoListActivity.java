package com.wenjian.myplayer.ui.classify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.network.model.VideoDetail;
import com.wenjian.myplayer.data.network.model.VideoListInfo;
import com.wenjian.myplayer.ui.base.AppBaseActivity;
import com.wenjian.myplayer.ui.home.SubRecyclerAdapter;
import com.wenjian.myplayer.widget.CommonTitleBar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 影片列表页
 *
 * @author wenjian
 */
public class VideoListActivity extends AppBaseActivity<VideoListContract.View, VideoListContract.Presenter>
        implements VideoListContract.View {

    private static final String EXTRA_CATALOG_ID = "catalogId";
    private static final String EXTRA_TITLE = "title";
    @BindView(R.id.title_bar)
    CommonTitleBar mTitleBar;
    @BindView(R.id.video_recycler)
    RecyclerView mVideoRecycler;

    private String mCatalogId;
    private SubRecyclerAdapter mAdapter;
    private String mTitle;
    private boolean isLoadMore = false;

    public static void start(Context context, String catalogId, String title) {
        Intent starter = new Intent(context, VideoListActivity.class);
        starter.putExtra(EXTRA_CATALOG_ID, catalogId);
        starter.putExtra(EXTRA_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_video_list;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        mCatalogId = bundle.getString(EXTRA_CATALOG_ID);
        mTitle = bundle.getString(EXTRA_TITLE);
        return !TextUtils.isEmpty(mCatalogId);
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mTitleBar.setTitle(mTitle);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mVideoRecycler.setLayoutManager(layoutManager);
        mAdapter = new SubRecyclerAdapter(new ArrayList<VideoDetail>());
        mVideoRecycler.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getVideoList(mCatalogId, isLoadMore = true);
            }
        }, mVideoRecycler);

    }

    @Override
    protected void initData() {
        getPresenter().getVideoList(mCatalogId, isLoadMore = false);
    }

    @Override
    public VideoListContract.Presenter createPresenter() {
        return new VideoListPresenter();
    }

    @Override
    public VideoListContract.View createView() {
        return this;
    }

    @Override
    public void onDataLoaded(VideoListInfo info) {
        if (isLoadMore) {
            mAdapter.addData(info.getList());
        } else {
            mAdapter.setNewData(info.getList());
        }
    }

    @Override
    public void onLoadMoreEnd() {
        mAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }


}
