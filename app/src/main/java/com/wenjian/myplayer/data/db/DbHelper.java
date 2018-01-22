package com.wenjian.myplayer.data.db;

import com.wenjian.myplayer.data.network.model.ShowDetail;
import com.wenjian.myplayer.data.network.model.VideoDetail;

/**
 * Description: DbHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public interface DbHelper {


    void saveVideos(VideoDetail... videoDetails);


    void saveShowDetail(ShowDetail... showDetails);


}
