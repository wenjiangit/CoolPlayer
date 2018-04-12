package com.wenjian.base.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wenjian.base.entity.VideoDetail;

/**
 * Description: NavigationUtils
 * Date: 2018/4/12
 *
 * @author jian.wen@ubtrobot.com
 */

public class NavigationUtils {


    public static void startVideoPlayActivity(VideoDetail detail) {
        ARouter.getInstance()
                .build("/videoplay/play")
                .withParcelable("video_src", detail)
                .navigation();
    }

    public static void startVideoPlayActivity(String dataId) {
        ARouter.getInstance()
                .build("/videoplay/play")
                .withString("data_id", dataId)
                .navigation();
    }

    public static void startVideoListActivity(String catalogId, String title) {
        ARouter.getInstance()
                .build("/videoplay/list")
                .withString("catalogId", catalogId)
                .withString("title", title)
                .navigation();
    }

}
