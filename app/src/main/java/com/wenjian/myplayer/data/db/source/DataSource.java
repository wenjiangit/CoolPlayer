package com.wenjian.myplayer.data.db.source;

import java.util.Collection;
import java.util.List;

/**
 * Description: DataSource
 * Date: 2018/2/1
 *
 * @author jian.wen@ubtrobot.com
 */

public interface DataSource<T> {

    interface LoadCallback<T> {

        void onDataLoaded(List<T> dataList);

        void onDataNotAvailable();
    }

    void saveList(Collection<T> dataList);

    void saveListAsync(Collection<T> dataList);

    void saveSingle(T data);

    List<T> loadAll();

    void loadAllAsync(LoadCallback<T> callback);

    void clearAll();

}
