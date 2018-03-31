package com.hss01248.adapter;

/**
 * Created by huangshuisheng on 2018/3/31.
 */

public interface IAdapter<A> {
    /**
     * 通知adapter更新当前页面的所有数据
     */
    void notifyDataSetChanged();

     CommonViewHolder generateNewHolder(A context, int position,int itemViewType,Class beanClass);
}
