package com.wenjian.myplayer.ui.finder;


import android.view.View;

import com.wenjian.core.ui.base.BaseFragment;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.widget.CommonTitleBar;

import butterknife.BindView;


/**
 * @author wenjian
 */
public class FinderFragment extends BaseFragment {

    @BindView(R.id.title_bar)
    CommonTitleBar mTitleBar;

    public FinderFragment() {
        // Required empty public constructor
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
    }
}
