package com.wenjian.myplayer.utils.rx;

import io.reactivex.Scheduler;

/**
 * Description: SchedulerProvider
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public interface SchedulerProvider {

    Scheduler io();

    Scheduler computation();

    Scheduler mainThread();


}
