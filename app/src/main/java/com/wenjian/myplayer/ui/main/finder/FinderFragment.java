package com.wenjian.myplayer.ui.main.finder;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.daprlabs.cardstack.SwipeDeck;
import com.daprlabs.cardstack.SwipeFrameLayout;
import com.wenjian.core.utils.ScreenUtils;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.entity.VideoDetail;
import com.wenjian.myplayer.ui.base.AppBaseFragment;
import com.wenjian.myplayer.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;


/**
 * 发现页面
 *
 * @author wenjian
 */
public class FinderFragment extends AppBaseFragment<FinderContract.View, FinderContract.Presenter>
        implements FinderContract.View {

    @BindView(R.id.title_bar)
    CommonTitleBar mTitleBar;
    @BindView(R.id.swipe_deck)
    SwipeDeck mSwipeDeck;
    @BindView(R.id.lay_swipe)
    SwipeFrameLayout mLaySwipe;
    private SwipeAdapter mSwipeAdapter;

    private static final String TAG = "FinderFragment";

    public FinderFragment() {
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
    protected void onFirstInit() {
        super.onFirstInit();
        getPresenter().loadData();
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
