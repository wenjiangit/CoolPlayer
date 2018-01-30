package com.wenjian.myplayer.data.db;

import java.util.Collection;
import java.util.List;

/**
 * Description: DbHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public interface DbHelper {


    /**
     * @param clz
     * @param ts
     * @param <T>
     */
    <T> void save(Class<T> clz, Collection<T> ts);

    /**
     * @param clz
     * @param t
     * @param <T>
     */
    <T> void save(Class<T> clz, T t);


    <T> void delete(Class<T> clz, Collection<T> ts);


    <T> void delete(Class<T> clz, T t);


    <T> List<T> loadAllSync(Class<T> clz);


    <T> void loadAllAsync(Class<T> clz, QueryCallback<T> callback);


    interface QueryCallback<T> {
        /**
         * 数据查询成功
         *
         * @param result 结果
         */
        void onQuerySuccess(List<T> result);


    }


}
