package com.wenjian.myplayer.ui.videoplay;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.entity.Comment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Description: CommentRecyclerAdapter
 * Date: 2018/1/12
 *
 * @author jian.wen@ubtrobot.com
 */

public class CommentRecyclerAdapter extends BaseQuickAdapter<Comment,BaseViewHolder>{
    public CommentRecyclerAdapter() {
        super(R.layout.cell_video_comment, new ArrayList<Comment>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment item) {
        helper.setText(R.id.tv_nick, item.getNickName());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_comment, item.getMsg());

        Glide.with(mContext)
                .load(item.getUserPic())
                .error(new ColorDrawable(Color.GRAY))
                .centerCrop()
                .into((CircleImageView) helper.getView(R.id.cv_pic));

    }
}
