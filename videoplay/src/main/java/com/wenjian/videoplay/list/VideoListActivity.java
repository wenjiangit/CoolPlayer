package com.wenjian.videoplay.list;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenjian.base.adapter.SubRecyclerAdapter;
import com.wenjian.base.data.db.source.collection.Collection;
import com.wenjian.base.entity.VideoDisplay;
import com.wenjian.base.entity.VideoListInfo;
import com.wenjian.base.ui.AppBaseActivity;
import com.wenjian.base.widget.CommonTitleBar;
import com.wenjian.videoplay.R;
import com.wenjian.videoplay.R2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * 影片列表页
 *
 * @author wenjian
 */
@Route(path = "/videoplay/list")
public class VideoListActivity extends AppBaseActivity<VideoListContract.View, VideoListContract.Presenter>
        implements VideoListContract.View {

    private static final String EXTRA_CATALOG_ID = "catalogId";
    private static final String EXTRA_TITLE = "title";
    public static final String TITLE_COLLECTION = "收藏";
    @BindView(R2.id.title_bar)
    CommonTitleBar mTitleBar;
    @BindView(R2.id.video_recycler)
    RecyclerView mVideoRecycler;

    private String mCatalogId;
    private SubRecyclerAdapter mAdapter;
    private String mTitle;
    private boolean isLoadMore = false;
    private boolean isCollection = false;

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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected boolean initArgs(Bundle bundle) {
        mCatalogId = bundle.getString(EXTRA_CATALOG_ID);
        mTitle = bundle.getString(EXTRA_TITLE);
        isCollection = Objects.equals(mTitle, TITLE_COLLECTION);
        return !TextUtils.isEmpty(mCatalogId) || isCollection;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mTitleBar.setTitle(mTitle);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mVideoRecycler.setLayoutManager(layoutManager);
        mAdapter = new SubRecyclerAdapter();
        mVideoRecycler.setAdapter(mAdapter);
        if (isCollection) {
            mAdapter.setEnableLoadMore(false);
        } else {
            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    getPresenter().getVideoList(mCatalogId, isLoadMore = true);
                }
            }, mVideoRecycler);
        }

    }

    @Override
    protected void initData() {
        if (isCollection) {
            getPresenter().loadAllCollections();
        } else {
            getPresenter().getVideoList(mCatalogId, isLoadMore = false);
        }
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
            mAdapter.setNewData(new ArrayList<VideoDisplay>(info.getList()));
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

    @Override
    public void onCollectionLoaded(List<Collection> collections) {
        mAdapter.setNewData(new ArrayList<VideoDisplay>(collections));
    }


}
