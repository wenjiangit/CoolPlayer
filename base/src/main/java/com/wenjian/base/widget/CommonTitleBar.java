package com.wenjian.base.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenjian.base.R;


/**
 * Description: CommonTitleBar
 * Date: 2018/1/12
 *
 * @author jian.wen@ubtrobot.com
 */

public class CommonTitleBar extends RelativeLayout implements View.OnClickListener {

    private ImageView mIvBack;
    private ImageView mIvClose;
    private TextView mTvTitle;
    private TextView mTvRightMenu;

    public CommonTitleBar(Context context) {
        super(context);
        init();
    }

    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.lay_title_bar, this);
        mIvBack = findViewById(R.id.iv_back);
        mIvClose = findViewById(R.id.iv_close);
        mTvTitle = findViewById(R.id.tv_title);
        mTvRightMenu = findViewById(R.id.tv_right_menu);

        mIvBack.setOnClickListener(this);
        mIvClose.setOnClickListener(this);
    }

    public void setTitle(CharSequence title) {
        mTvTitle.setText(title);
    }

    public void setBacklistener(OnClickListener listener) {
        mIvBack.setOnClickListener(listener);
    }

    public void setCloseListener(OnClickListener listener) {
        mIvClose.setOnClickListener(listener);
    }

    public void addRightMenu(@NonNull CharSequence text, OnClickListener listener) {
        mTvRightMenu.setOnClickListener(listener);
        mTvRightMenu.setText(text);
    }

    public TextView getRightMenu() {
        return mTvRightMenu;
    }

    public void setRightMenuText(@NonNull CharSequence text) {
        mTvRightMenu.setText(text);
    }

    public void setCanback(boolean canback) {
        if (canback) {
            mIvBack.setVisibility(VISIBLE);
        } else {
            mIvBack.setVisibility(GONE);
        }
    }


    @Override
    public void onClick(View v) {
        Context context = getContext();
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

}
