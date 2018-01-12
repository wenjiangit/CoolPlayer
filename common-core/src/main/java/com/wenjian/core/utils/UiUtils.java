package com.wenjian.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Description: UiUtils
 * Date: 2017/12/19
 *
 * @author jian.wen@ubtrobot.com
 */

public class UiUtils {

    private UiUtils() {

    }


    /**
     * Change Dip to PX
     *
     * @param resources Resources
     * @param dp        Dip
     * @return PX
     */
    public static float dip2Px(Resources resources, float dp) {
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }


    /**
     * Change Dip to PX
     *
     * @param dp Dip
     * @return PX
     */
    public static float dip2Px(float dp) {
        return dip2Px(Utils.getAppContext().getResources(), dp);
    }

    /**
     * Change SP to PX
     *
     * @param resources Resources
     * @param sp        SP
     * @return PX
     */
    public static float sp2Px(Resources resources, float sp) {
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
    }

    /**
     * Change SP to PX
     *
     * @param sp SP
     * @return PX
     */
    public static float sp2Px(float sp) {
        return sp2Px(Utils.getAppContext().getResources(), sp);
    }


}
