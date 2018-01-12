package com.wenjian.core.widget.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 默认的分割线,使用系统的listDivider
 *
 * @author wenjian
 * @date 2017/5/12
 */

public class DefaultItemDecoration extends RecyclerView.ItemDecoration {

    private final Drawable mDrawable;

    public DefaultItemDecoration(Context context) {
        int[] attrs = new int[]{android.R.attr.listDivider};
        TypedArray array = context.obtainStyledAttributes(attrs);
        mDrawable = array.getDrawable(0);
        array.recycle();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildLayoutPosition(view);

        //除了第一个之外,给每个条目留出间隙--> drawable的高度
        if (position != 0) {
            outRect.top = mDrawable.getIntrinsicHeight();
        }
    }


    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int bottom;
        int top;

        int childCount = parent.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = parent.getChildAt(i);
            bottom = child.getTop();
            top = bottom - mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(canvas);
        }

    }
}
