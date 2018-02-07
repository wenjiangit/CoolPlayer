package com.wenjian.core.widget;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenjian.common_core.R;
import com.wenjian.core.utils.UiUtils;


/**
 * Description: DataItemView
 * Date: 2017/12/25
 *
 * @author jian.wen@ubtrobot.com
 */

public class DataItemView extends LinearLayout {

    private TextView mTvTitle;
    private View mArrow;
    private View mTopLine;
    private View mBottomLine;
    private TextView mTvCenterTitle;
    private Builder mBuilder;

    public DataItemView(Context context) {
        this(context, null);
    }

    public DataItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.cell_data_item, this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvCenterTitle = (TextView) findViewById(R.id.tv_center_title);
        mArrow = findViewById(R.id.tv_arrow);
        mTopLine = findViewById(R.id.top_line);
        mBottomLine = findViewById(R.id.bottom_line);
    }


    private void apply(Builder builder) {
        this.mBuilder = builder;

        if (builder.hasTop) {
            mTopLine.setVisibility(VISIBLE);
        }
        if (!builder.fullBottom) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mBottomLine.getLayoutParams();
            layoutParams.leftMargin = (int) UiUtils.dip2Px(builder.context.getResources(), 10);
            mBottomLine.setLayoutParams(layoutParams);
        }

        if (builder.enable) {
            mArrow.setVisibility(VISIBLE);
        } else {
            mArrow.setVisibility(GONE);
        }

        if (builder.enable && builder.clickListener != null) {
            this.setOnClickListener(builder.clickListener);
            this.setClickable(true);
        }

        if (builder.id != -1) {
            this.setId(builder.id);
        }

        if (builder.buttonStyle) {
            mTvCenterTitle.setText(builder.title);
            mTvTitle.setVisibility(GONE);
            mArrow.setVisibility(GONE);
        } else {
            mTvTitle.setText(builder.title);
            mTvCenterTitle.setVisibility(GONE);
        }
    }

    /**
     * 获取标题
     *
     * @return 标题
     */
    public CharSequence getTitle() {
        return mBuilder.title;
    }

    /**
     * 创建Builder对象
     *
     * @param context Context
     * @return Builder
     */
    public static Builder buider(Context context) {
        return new Builder(context);
    }

    /**
     * 提供为LayoutParams设置margin的快捷方法
     *
     * @param context Context
     * @param margin  margin值，内部已进行了转换
     * @return LinearLayout.LayoutParams
     */
    public static LayoutParams createLayoutParams(Context context, int margin) {
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.topMargin = (int) UiUtils.dip2Px(context.getResources(), margin);
        return params;
    }

    public static class Builder {

        @NonNull
        Context context;
        boolean hasTop = false;
        boolean fullBottom = false;
        boolean enable = true;
        CharSequence title;
        OnClickListener clickListener;
        boolean buttonStyle = false;
        int id = -1;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public DataItemView build() {
            DataItemView itemView = new DataItemView(this.context);
            itemView.apply(this);
            return itemView;
        }

        public Builder topLine() {
            this.hasTop = true;
            return this;
        }

        public Builder fullBottom() {
            this.fullBottom = true;
            return this;
        }

        public Builder title(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder title(@StringRes int resId) {
            return title(context.getString(resId));
        }

        public Builder next(OnClickListener listener) {
            return next(-1, listener);
        }

        /**
         * 设置点击事件并可设置id
         *
         * @param id       资源id
         * @param listener OnClickListener
         * @return Builder
         */
        public Builder next(@IdRes int id, OnClickListener listener) {
            this.clickListener = listener;
            this.id = id;
            return this;
        }

        public Builder enable(boolean enable) {
            this.enable = enable;
            return this;
        }

        /**
         * 设置成按钮样式
         *
         * @return Builder
         */
        public Builder buttonStyle() {
            this.buttonStyle = true;
            return this;
        }


    }


}
