package com.wenjian.myplayer.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: AppSchedulerProvider
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppSchedulerProvider implements SchedulerProvider{


    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
