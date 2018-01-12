package com.wenjian.core.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

/**
 * Description: AnimationUtils
 * Date: 2017/12/18
 *
 * @author jian.wen@ubtrobot.com
 */

public class AnimationUtils {


    /**
     * 默认的动画执行时间
     */
    private static final int DEFAULT_ANIM_DURATION = 300;


    /**
     * 逐渐显示
     *
     * @param viewRoot 开启动画的根view
     * @param duration 时长
     * @param adapter  动画监听
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealShow(View viewRoot, int duration, AnimatorListenerAdapter adapter) {
        Animator anim = createRevealOfCenter(viewRoot, true);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(duration);
        anim.setInterpolator(new AccelerateInterpolator());
        if (adapter != null) {
            anim.addListener(adapter);
        }
        anim.start();
    }


    public static void animateRevealShow(View viewRoot) {
        animateRevealShow(viewRoot, DEFAULT_ANIM_DURATION, null);
    }

    public static void animateRevealShow(View viewRoot, AnimatorListenerAdapter adapter) {
        animateRevealShow(viewRoot, DEFAULT_ANIM_DURATION, adapter);
    }

    /**
     * 逐渐隐藏
     *
     * @param viewRoot 执行动画的view
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealHide(final View viewRoot) {
        Animator anim = createRevealOfCenter(viewRoot, false);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                viewRoot.setVisibility(View.INVISIBLE);
            }
        });
        anim.setDuration(DEFAULT_ANIM_DURATION);
        anim.start();
    }

    /**
     * 从指定坐标点开启一个揭露动画，并可设置view的背景颜色
     *
     * @param viewRoot 根view
     * @param color    颜色
     * @param x        x坐标
     * @param y        y坐标
     * @return Animator
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        Animator anim = createRevealOfCoordinates(viewRoot, x, y, true);
        viewRoot.setBackgroundColor(ContextCompat.getColor(Utils.getAppContext(), color));
        anim.setDuration(DEFAULT_ANIM_DURATION);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }

    /**
     * 从view的中心创建一个揭露动画
     *
     * @param viewRoot 根view
     * @param expand   从小到大扩展
     * @return Animator
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator createRevealOfCenter(View viewRoot, boolean expand) {
        final int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        final int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        final int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());
        if (expand) {
            return ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0, finalRadius);
        } else {
            return ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, finalRadius, 0);
        }
    }


    /**
     * 从某个坐标点创建一个揭露动画
     *
     * @param viewRoot 根view
     * @param x        x坐标
     * @param y        y坐标
     * @param expand   扩展(true代表从小到大扩展，false则相反)
     * @return Animator
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator createRevealOfCoordinates(View viewRoot, int x, int y, boolean expand) {
        final float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());
        if (expand) {
            return ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        } else {
            return ViewAnimationUtils.createCircularReveal(viewRoot, x, y, finalRadius, 0);
        }
    }

}
