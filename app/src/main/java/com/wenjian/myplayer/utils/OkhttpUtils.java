package com.wenjian.myplayer.utils;

import android.support.annotation.NonNull;

import com.wenjian.core.utils.NetworkUtils;
import com.wenjian.myplayer.Application;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description: OkhttpUtils
 * Date: 2018/1/16
 *
 * @author jian.wen@ubtrobot.com
 */

public class OkhttpUtils {

    private static final int CONNECT_TIMEOUT = 20;
    private static final int READ_TIMEOUT = 20;
    private static final int WRITE_TIMEOUT = 20;
    private static final int MAX_DISK_CACHE_SIZE = 1024 * 1024 * 50;
    private static final int MAX_STALE = 24 * 60 * 60 * 28;

    public static OkHttpClient getDefault() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        addCacheControl(builder);
        return builder.build();
    }


    /**
     * 添加okhttp缓存设置
     *
     * @param builder Builder
     */
    private static void addCacheControl(OkHttpClient.Builder builder) {
        Cache cache = new Cache(new File(Application.getContext().getCacheDir(), "network"), MAX_DISK_CACHE_SIZE);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                //没网强制走缓存
                if (!NetworkUtils.isNetworkAvailable()) {
                    request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();

                }

                Response response = null;
                try {
                    response = chain.proceed(request);
                    //有网不缓存,最大保存时长为0
                    if (NetworkUtils.isNetworkAvailable()) {
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + 0)
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        //没网进行,保存时间为4周
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + MAX_STALE)
                                .removeHeader("Pragma")
                                .build();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return response;
            }
        };
        builder.cache(cache);
        builder.addInterceptor(cacheInterceptor);
        builder.addNetworkInterceptor(cacheInterceptor);
    }
}
