package com.wenjian.core.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenjian.common_core.R;
import com.wenjian.core.utils.UiUtils;

/**
 * Description: ExpandTextView
 * Date: 2018/1/11
 *
 * @author jian.wen@ubtrobot.com
 */

public class ExpandTextView extends FrameLayout {

    private static final String TAG = "ExpandTextView";

    private TextView mTextView;

    private ImageView mIndicator;

    private boolean expand = false;

    private int mExpandLineCount = 2;

    private OnExpandChangeListener mExpandChangeListener;


    private static final FrameLayout.LayoutParams MATCH_PARAMS;

    static {
        MATCH_PARAMS = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private int mLineCount;
    private int mBaseLine1;

    public ExpandTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public ExpandTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExpandTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextView = new TextView(getContext());
        this.addView(mTextView, MATCH_PARAMS);

        mIndicator = new ImageView(getContext());
        mIndicator.setImageResource(R.drawable.ic_expand_more);
        FrameLayout.LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 80;
        layoutParams.gravity = Gravity.TOP | Gravity.RIGHT;
        this.addView(mIndicator, layoutParams);

        mIndicator.setMinimumWidth(100);
        mIndicator.setMinimumHeight(100);
        mIndicator.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mIndicator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expand) {
                    mTextView.setMaxLines(mExpandLineCount);
                    mIndicator.setImageResource(R.drawable.ic_expand_more);
                } else {
                    mTextView.setMaxLines(mLineCount);
                    mIndicator.setImageResource(R.drawable.ic_expand_less);
                }
                expand = !expand;
                if (mExpandChangeListener != null) {
                    mExpandChangeListener.onExpandChange(expand);
                }
            }
        });

        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ExpandTextView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                mLineCount = mTextView.getLineCount();
                mBaseLine1 = mTextView.getLineBounds(1, null);
                FrameLayout.LayoutParams layoutParams = (LayoutParams) mIndicator.getLayoutParams();
                layoutParams.rightMargin = 20;
                layoutParams.topMargin = mBaseLine1 - mIndicator.getHeight();
                Log.d(TAG, "lineCount: " + mLineCount);
                if (mLineCount > mExpandLineCount) {
                    mTextView.setMaxLines(mExpandLineCount);
                    mIndicator.setVisibility(VISIBLE);
                    expand = false;
                } else {
                    mIndicator.setVisibility(GONE);
                }
                return true;
            }
        });
    }


    public void setText(CharSequence text) {
        mTextView.setText(text);
    }

    public void setText(@StringRes int resId) {
        setText(getContext().getString(resId));
    }

    public void setTextSize(float size) {
        mTextView.setTextSize(size);
    }

    public void setTextSpSize(float size) {
        this.setTextSize(UiUtils.sp2Px(size));
    }

    public void setTextColor(int color) {
        mTextView.setTextColor(color);
    }

    public void setExtraLineSpacing(float spacing) {
        mTextView.setLineSpacing(spacing, 1.0f);
    }

    public void setExpandChangeListener(OnExpandChangeListener expandChangeListener) {
        mExpandChangeListener = expandChangeListener;
    }

    public interface OnExpandChangeListener {

        void onExpandChange(boolean expand);
    }

}
