package com.hss01248.superadapter;

import com.hss01248.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/12/1.
 */

public class MyViewHolder extends BaseViewHolder<MainActivity,Bean1> {
    public MyViewHolder(MainActivity activity) {
        super(activity);
    }

    @Override
    protected int setLayoutRes() {
        return 0;
    }

    @Override
    public void assingDatasAndEvents(Bean1 bean) {

    }
}
