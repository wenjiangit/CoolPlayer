package com.wenjian.myplayer.data.network;

import com.wenjian.myplayer.BuildConfig;

/**
 * Description: ApiEndPoint
 * Date: 2018/1/9
 *
 * @author jian.wen@ubtrobot.com
 */

public interface ApiEndPoint {

    String HOME_PAGER = BuildConfig.BASE_URL + "homePageApi/homePage.do";


    String VIDEO_COMMENT = BuildConfig.BASE_URL + "Commentary/getCommentList.do";
}
