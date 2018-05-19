package com.hss01248.superadapter;


import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.hss01248.adapter.CommonViewHolder;

/**
 * Created by Administrator on 2017/12/1.
 */

public class MyViewHolder extends CommonViewHolder<Bean1,MainActivity> {


    public MyViewHolder(MainActivity context, ViewGroup viewGroup) {
        super(context, viewGroup);
    }

    @Override
    protected void findViewsById(View rootView) {

    }

    @Override
    protected int setLayoutRes() {
        return 0;
    }

    @Override
    public void assingDatasAndEvents(MainActivity activity, @Nullable Bean1 bean) {

    }

}
