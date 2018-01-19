package com.wenjian.myplayer.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Description: ZoomInScrollView
 * Date: 2018/1/17
 *
 * @author jian.wen@ubtrobot.com
 */

public class ZoomInScrollView extends ScrollView {

    private View mHeaderView;
    private boolean mIsPulling;
    private int mHeaderWidth;
    private int mHeaderHeight;

    public ZoomInScrollView(Context context) {
        super(context);
    }

    public ZoomInScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomInScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOverScrollMode(OVER_SCROLL_NEVER);
        View child = getChildAt(0);
        if (child != null && child instanceof ViewGroup) {
            mHeaderView = ((ViewGroup) child).getChildAt(0);
        }
    }

    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mHeaderView == null) {
            return super.onTouchEvent(ev);
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mIsPulling) {
                    if (getScrollY() == 0) {
                        mLastY = (int) ev.getY();
                    } else {
                        break;
                    }
                }

                if (ev.getY() < mLastY) {
                    return super.onTouchEvent(ev);
                }

                int distance = (int) (ev.getY() - mLastY);
                mIsPulling = true;
                setZoom(distance);
                break;
            case MotionEvent.ACTION_DOWN:
                mIsPulling = false;
                replayView();
                break;
                default:
        }

        return super.onTouchEvent(ev);
    }

    private void replayView() {
        final float distance = mHeaderView.getMeasuredWidth() - mHeaderWidth;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * 0.5f));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoom((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    public void setZoom(float distance) {
        float scaleTimes = (float) ((mHeaderWidth + distance) / (mHeaderWidth * 1.0));
//        如超过最大放大倍数，直接返回
        if (scaleTimes > 2) return;

        ViewGroup.LayoutParams layoutParams = mHeaderView.getLayoutParams();
        layoutParams.width = (int) (mHeaderWidth + distance);
        layoutParams.height = (int) (mHeaderHeight * ((mHeaderWidth + distance) / mHeaderWidth));
//        设置控件水平居中
        ((MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - mHeaderWidth) / 2, 0, 0, 0);
        mHeaderView.setLayoutParams(layoutParams);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeaderWidth = mHeaderView.getMeasuredWidth();
        mHeaderHeight = mHeaderView.getMeasuredHeight();
    }
}
