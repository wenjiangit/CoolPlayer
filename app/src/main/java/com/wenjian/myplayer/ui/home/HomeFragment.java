package com.wenjian.myplayer.ui.home;


import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.network.model.ShowDetail;
import com.wenjian.myplayer.ui.base.AppBaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: HomeFragment
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class HomeFragment extends AppBaseFragment<HomeContract.View, HomeContract.Presenter>
        implements HomeContract.View {

    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;


    private HomeRecyclerAdapter mAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    protected Object getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);
        setupRecycler();
        setupSwipeRefreshLayout();
    }

    private void setupSwipeRefreshLayout() {
        mSwipeRefresh.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);
        mSwipeRefresh.setProgressViewOffset(true,0,200);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().start();
            }
        });
    }


    @OnClick(R.id.tv_search)
    void onSearchClick() {
        // TODO: 2018/1/11 search
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter.getBanner() != null) {
            mAdapter.getBanner().start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAdapter.getBanner() != null) {
            mAdapter.getBanner().pause();
        }
    }

    private void setupRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mHomeRecycler.setLayoutManager(layoutManager);
        mAdapter = new HomeRecyclerAdapter();
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mHomeRecycler.setAdapter(mAdapter);
        mHomeRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private float y;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                y += dy;
                View childAt = recyclerView.getChildAt(0);
                int height = childAt.getHeight() - mAppBarLayout.getHeight();
                if (y < height) {
                    float fraction = y / height;
                    int alpha = (int) (fraction * 255);
                    mAppBarLayout.setBackgroundColor(Color.argb(alpha, 249, 141, 44));
                } else {
                    mAppBarLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                }
            }
        });
    }


    @Override
    protected void onFirstInit() {
        getPresenter().start();
    }

    @Override
    public HomeContract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public HomeContract.View createView() {
        return this;
    }

    @Override
    public void showLoading() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onLoadSuccess(List<ShowDetail> list) {
        mAdapter.setNewData(list);
    }

}
