package com.wenjian.myplayer.ui.finder;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.network.model.VideoDetail;
import com.wenjian.myplayer.ui.videoplay.VideoPlayActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: SwipeAdapter
 * Date: 2018/1/30
 *
 * @author jian.wen@ubtrobot.com
 */

public class SwipeAdapter extends BaseAdapter {

    private List<VideoDetail> mData;

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<VideoDetail> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_swip_video, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bind(mData.get(position));
        return convertView;
    }


    static class ViewHolder{
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_desc)
        TextView mTvDesc;
        @BindView(R.id.iv_thumb)
        ImageView mIvThumb;
        @BindView(R.id.card_view)
        CardView mCardView;

        private View mView;

        ViewHolder(View itemView) {
            this.mView = itemView;
            ButterKnife.bind(this,itemView);
        }

        void bind(final VideoDetail detail) {
            mTvTitle.setText(detail.getTitle());
            mTvDesc.setText(detail.getDescription());
            Glide.with(mView.getContext())
                    .load(detail.getPic())
                    .into(mIvThumb);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VideoPlayActivity.start(mView.getContext(),detail);
                }
            });
        }

    }





}
