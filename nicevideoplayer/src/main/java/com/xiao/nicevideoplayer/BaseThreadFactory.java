package com.xiao.nicevideoplayer;

import android.support.annotation.NonNull;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: BaseThreadFactory
 * Date: 2018/1/15
 *
 * @author jian.wen@ubtrobot.com
 */

public class BaseThreadFactory implements ThreadFactory {

    private static AtomicInteger mAtomicInteger = new AtomicInteger();

    private Builder mBuilder;

    BaseThreadFactory(Builder builder) {
        this.mBuilder = builder;
    }

    public static Builder builder() {
        return new Builder();
    }


    @Override
    public Thread newThread(@NonNull final Runnable r) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                r.run();
            }
        });
        thread.setDaemon(mBuilder.daemon);
        thread.setName(String.format(Locale.getDefault(), mBuilder.namePattern, mAtomicInteger.getAndIncrement()));
        thread.setPriority(mBuilder.priority);
        return thread;
    }


    public static class Builder {

        String namePattern = "thread-pool-%d";

        boolean daemon = false;

        int priority = Thread.NORM_PRIORITY;


        public BaseThreadFactory build() {
            return new BaseThreadFactory(this);
        }

        public Builder daemon(boolean daemon) {
            this.daemon = daemon;
            return this;
        }

        public Builder namePattern(String namePattern) {
            this.namePattern = namePattern;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }
    }
}
