package com.wenjian.myplayer.ui.mine;


import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wenjian.core.ui.base.BaseFragment;
import com.wenjian.core.utils.Logger;
import com.wenjian.myplayer.R;

import butterknife.BindView;

/**
 * 我的
 *
 * @author wenjian
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.iv_img)
    ImageView mIvImg;

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

        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Logger.d("verticalOffset", "verticalOffset: %d", verticalOffset);

                if (verticalOffset == 0) {
                    //完全展开
                    mIvImg.setScaleX(1.5f);
                    mIvImg.setScaleY(1.5f);

                } else {
                    verticalOffset = Math.abs(verticalOffset);
                    int totalScrollRange = appBarLayout.getTotalScrollRange();
                    if (verticalOffset >= totalScrollRange) {//完全收缩
                        mIvImg.setScaleX(1);
                        mIvImg.setScaleY(1);
                    } else {
                        float progress = (float) (1 + (verticalOffset / (float) totalScrollRange) * 0.5);
                        mIvImg.setScaleX(progress);
                        mIvImg.setScaleY(progress);
                    }
                }
            }
        });






    }


}
