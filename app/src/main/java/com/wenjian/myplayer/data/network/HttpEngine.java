package com.wenjian.myplayer.data.network;

import com.google.gson.GsonBuilder;
import com.wenjian.myplayer.BuildConfig;
import com.wenjian.myplayer.utils.OkhttpUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: HttpEngine
 * Date: 2018/2/11
 *
 * @author jian.wen@ubtrobot.com
 */

public class HttpEngine {

    private final Retrofit mRetrofit;

    private MovieService mService;

    private HttpEngine() {
        this.mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation().create()))
                .client(OkhttpUtils.getDefault())
                .build();
    }

    private static class Holder {
        private static final HttpEngine INSTANCE = new HttpEngine();
    }

    public static HttpEngine getInstance() {
        return Holder.INSTANCE;
    }

    public MovieService service() {
        if (mService != null) {
            return mService;
        } else {
            synchronized (this) {
                if (mService == null) {
                    mService = new MovieServiceImpl(mRetrofit.create(MovieService.class));
                }
            }
            return mService;
        }
    }

}
