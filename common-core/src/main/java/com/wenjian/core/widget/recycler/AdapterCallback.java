package com.wenjian.core.widget.recycler;

/**
 * AdapterCallback
 *
 * @author wenjian
 * @date 2017/6/2
 */

public interface AdapterCallback<Data> {

    /**
     * 更新一条数据
     *
     * @param data 数据
     * @param holder ViewHolder
     */
    void update(Data data, BaseRecyclerAdapter.ViewHolder<Data> holder);
}
