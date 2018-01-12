package com.wenjian.core.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 解决fitSystemWindow导致的状态栏灰色 ，并导致沉浸式效果失效
 *
 * @author douliu
 * @date 2017/7/6
 */

public class FitSystemLayout extends LinearLayout {
    public FitSystemLayout(Context context) {
        super(context);
    }

    public FitSystemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FitSystemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FitSystemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            insets.left = 0;
            insets.right = 0;
            insets.top = 0;
        }
        return super.fitSystemWindows(insets);
    }

}
