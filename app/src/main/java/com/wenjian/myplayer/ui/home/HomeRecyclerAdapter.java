package com.wenjian.myplayer.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenjian.core.utils.Logger;
import com.wenjian.core.utils.UiUtils;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.network.model.ShowDetail;
import com.wenjian.myplayer.data.network.model.VideoDetail;
import com.wenjian.myplayer.ui.videoplay.VideoPlayActivity;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: HomeRecyclerAdapter
 * Date: 2018/1/9
 *
 * @author jian.wen@ubtrobot.com
 */

public class HomeRecyclerAdapter extends BaseMultiItemQuickAdapter<ShowDetail, BaseViewHolder> {

    private MZBannerView<VideoDetail> mBanner;

    public HomeRecyclerAdapter() {
        super(new ArrayList<ShowDetail>());

        addItemType(ShowDetail.TYPE_NORMAL, R.layout.cell_home_recycler);
        addItemType(ShowDetail.TYPE_BANNER, R.layout.cell_home_banner);
        addItemType(ShowDetail.TYPE_ADV, R.layout.cell_home_adv);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShowDetail item) {
        switch (helper.getItemViewType()) {
            case ShowDetail.TYPE_BANNER:
                mBanner = helper.getView(R.id.banner);
                mBanner.setPages(item.getChildList(), new MZHolderCreator() {
                    @Override
                    public MZViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                mBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
                    @Override
                    public void onPageClick(View view, int i) {
                        VideoPlayActivity.start(mContext,item.getChildList().get(i));
                    }
                });
                mBanner.start();
                break;

            case ShowDetail.TYPE_NORMAL:
                helper.setText(R.id.tv_title, item.getTitle());

                RecyclerView recycler = helper.getView(R.id.item_recycler);
                GridLayoutManager layoutManager = new GridLayoutManager(mContext, getSpanCount(item));
                recycler.setLayoutManager(layoutManager);
                final SubRecyclerAdapter adapter = new SubRecyclerAdapter(item.getChildList());
                recycler.setAdapter(adapter);
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int size = adapter.getData().size();
                        if (size == 7 && position == 0) {
                            return 3;
                        }
                        return 1;
                    }
                });
                break;
            default:
                helper.itemView.setVisibility(View.GONE);
                break;
        }
    }

    private int getSpanCount(ShowDetail item) {
        final int childCount = item.getChildList().size();
        if (childCount < 3) {
            return childCount;
        } else {
            return 3;
        }
    }

    public MZBannerView<VideoDetail> getBanner() {
        return mBanner;
    }

    private static class BannerViewHolder implements MZViewHolder<VideoDetail> {

        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            mImageView = new ImageView(context);
            return mImageView;
        }

        @Override
        public void onBind(Context context, int i, VideoDetail videoDetail) {
            Glide.with(context)
                    .load(videoDetail.getPic())
                    .placeholder(new ColorDrawable(Color.GRAY))
                    .into(mImageView);

        }
    }

}
