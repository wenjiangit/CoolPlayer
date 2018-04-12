package com.wenjian.base.utils.rx;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: RxUtils
 * Date: 2018/2/28
 *
 * @author jian.wen@ubtrobot.com
 */

public class RxUtils {


    public static <T> FlowableTransformer<T, T> transfor() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> Observable<T> createObservable(final T obj) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                if (!e.isDisposed()) {
                    try {
                        e.onNext(obj);
                        e.onComplete();
                    } catch (Exception e1) {
                        e.onError(e1);
                    }
                }
            }
        });
    }


}
