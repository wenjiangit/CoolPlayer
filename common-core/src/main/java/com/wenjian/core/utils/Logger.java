/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.wenjian.core.utils;


import com.wenjian.common_core.BuildConfig;

import timber.log.Timber;

/**
 * @author wenjian
 * @date 15/02/17
 */

public class Logger {

    public static void init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void d(String tag, String msg, Object... objects) {
        Timber.tag(tag).d(msg, objects);
    }

    public static void d(Throwable throwable, String s, Object... objects) {
        Timber.d(throwable, s, objects);
    }

    public static void i(String tag, String msg, Object... objects) {
        Timber.tag(tag).i(msg, objects);
    }

    public static void i(Throwable throwable, String s, Object... objects) {
        Timber.i(throwable, s, objects);
    }

    public static void w(String tag, Object... objects) {
        Timber.w(tag, objects);
    }

    public static void w(Throwable throwable, String tag, Object... objects) {
        Timber.w(throwable, tag, objects);
    }

    public static void e(String tag, Object... objects) {
        Timber.e(tag, objects);
    }

    public static void e(Throwable throwable, String tag, String msg, Object... objects) {
        Timber.tag(tag).e(throwable, msg, objects);
    }

}
