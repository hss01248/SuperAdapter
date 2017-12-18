package com.hss01248.superadapter;

import android.app.Activity;

import com.hss01248.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/12/1.
 */

public class MyViewHolder2 extends BaseViewHolder<Activity,Bean1> {
    public MyViewHolder2(Activity activity) {
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
