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


    String VIDEO_LIST = BuildConfig.BASE_URL + "columns/getVideoList.do";


    String POPU_MSG = BuildConfig.BASE_URL + "popUpApi/popUpMsg.do";


    String HOT_SEARCH = BuildConfig.BASE_URL + "hotSearchApi/hotSearch.do";


    String FIND_MOVIE_PAGE = BuildConfig.BASE_URL + "find/findMoviePage.do";


    String FIND_PAGE_INFO = BuildConfig.BASE_URL + "find/findPageInfoMsg.do";


    String VIDEO_DETAIL = BuildConfig.BASE_URL + "videoDetailApi/videoDetail.do";


}
