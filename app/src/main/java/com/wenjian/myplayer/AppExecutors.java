package com.wenjian.myplayer;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description: AppExecutors
 * Date: 2018/1/31
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppExecutors {

    private final Executor mIoDisk;

    private final Executor mMainThread;

    private final Executor mNetwork;

    private static class Holder {
        private static final AppExecutors INSTANCE = new AppExecutors();
    }

    public static AppExecutors getInstance() {
        return Holder.INSTANCE;
    }


    public AppExecutors(Executor ioDisk, Executor mainThread, Executor network) {
        mIoDisk = ioDisk;
        mMainThread = mainThread;
        mNetwork = network;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), new MainThreadExecutor(), Executors.newFixedThreadPool(3));
    }


    public void forMainThread(Runnable command) {
        mMainThread.execute(command);
    }

    public void forDiskIO(Runnable command) {
        mIoDisk.execute(command);
    }

    public void forNetworkIO(Runnable command) {
        mNetwork.execute(command);
    }


    static class MainThreadExecutor  implements Executor {

        Handler mHandler = new Handler();

        MainThreadExecutor() {
        }

        @Override
        public void execute(@NonNull Runnable command) {
            mHandler.post(command);
        }
    }
}
