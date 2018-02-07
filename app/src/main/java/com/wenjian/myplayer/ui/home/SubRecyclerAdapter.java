package com.wenjian.myplayer.ui.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenjian.core.utils.UiUtils;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.network.model.VideoDisplay;
import com.wenjian.myplayer.ui.videoplay.VideoPlayActivity;

/**
 * Description: SubRecyclerAdapter
 * Date: 2018/1/11
 *
 * @author jian.wen@ubtrobot.com
 */

public class SubRecyclerAdapter extends BaseQuickAdapter<VideoDisplay, BaseViewHolder> {
    public SubRecyclerAdapter() {
        super(R.layout.cell_home_recylcer_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final VideoDisplay item) {
        helper.setText(R.id.tv_name, item.getTitle());
        ImageView ivThumb = helper.getView(R.id.iv_thumb);
        Glide.with(mContext)
                .load(item.getThumb())
                .into(ivThumb);

        ViewGroup itemView = (ViewGroup) helper.itemView;
        View childAt0 = itemView.getChildAt(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (getData().size() == 2) {
            layoutParams.height = (int) UiUtils.dip2Px(110);
        } else {
            layoutParams.height = (int) UiUtils.dip2Px(170);
        }
        childAt0.setLayoutParams(layoutParams);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayActivity.start(mContext, item.getId());
            }
        });
    }
}
