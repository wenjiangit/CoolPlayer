package com.wenjian.core.thread;

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

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    private final String namePattern;
    private final boolean daemon;
    private final int priority;

    BaseThreadFactory(Builder builder) {
        this.daemon = builder.daemon;
        this.namePattern = builder.namePattern;
        this.priority = builder.priority;
    }

    @Override
    public Thread newThread(@NonNull final Runnable r) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                r.run();
            }
        });
        thread.setDaemon(daemon);
        thread.setName(String.format(Locale.getDefault(), namePattern, ATOMIC_INTEGER.getAndIncrement()));
        thread.setPriority(priority);
        return thread;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static class Builder {

        private String namePattern = "thread-pool-%d";

        private boolean daemon = false;

        private int priority = Thread.NORM_PRIORITY;

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
