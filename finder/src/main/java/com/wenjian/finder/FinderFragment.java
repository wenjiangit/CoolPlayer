package com.wenjian.finder;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.daprlabs.cardstack.SwipeDeck;
import com.daprlabs.cardstack.SwipeFrameLayout;
import com.wenjian.base.entity.VideoDetail;
import com.wenjian.base.ui.AppBaseFragment;
import com.wenjian.base.utils.ScreenUtils;
import com.wenjian.base.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;


/**
 * 发现页面
 *
 * @author wenjian
 */
@Route(path = "/finder/FinderFragment")
public class FinderFragment extends AppBaseFragment<FinderContract.View, FinderContract.Presenter>
        implements FinderContract.View {

    private static final String TAG = "FinderFragment";
    @BindView(R2.id.title_bar)
    CommonTitleBar mTitleBar;
    @BindView(R2.id.swipe_deck)
    SwipeDeck mSwipeDeck;
    @BindView(R2.id.lay_swipe)
    SwipeFrameLayout mLaySwipe;
    private SwipeAdapter mSwipeAdapter;

    public FinderFragment() {
    }

    @Override
    protected void onFirstLoad() {
        super.onFirstLoad();
        getPresenter().loadData();
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.fragment_finder;
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);
        mTitleBar.setTitle("发现");
        mTitleBar.setCanback(false);

        ViewGroup.LayoutParams params = mSwipeDeck.getLayoutParams();
        params.height = ScreenUtils.getScreenHeight() * 2 / 3;
        mSwipeDeck.setLayoutParams(params);

        mSwipeDeck.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.d(TAG, "cardSwipedLeft: " + position);

            }

            @Override
            public void cardSwipedRight(int position) {
                Log.d(TAG, "cardSwipedRight: " + position);

            }

            @Override
            public void cardsDepleted() {
                Log.d(TAG, "cardsDepleted: ");
                getPresenter().loadData();

            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });

    }

    @Override
    public FinderContract.Presenter createPresenter() {
        return new FinderPresenter();
    }

    @Override
    public FinderContract.View createView() {
        return this;
    }

    @Override
    public void onLoadSuccess(List<VideoDetail> details) {
        mSwipeDeck.removeAllViews();
        mSwipeAdapter = new SwipeAdapter();
        mSwipeAdapter.setData(details);
        mSwipeDeck.setAdapter(mSwipeAdapter);
    }

}
