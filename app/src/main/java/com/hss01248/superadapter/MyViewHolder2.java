package com.hss01248.superadapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;

import com.hss01248.adapter.CommonViewHolder;

/**
 * Created by Administrator on 2017/12/1.
 */

public class MyViewHolder2 extends CommonViewHolder<Bean1,Activity> {
    public MyViewHolder2(Activity activity) {
        super(activity);
    }

    @Override
    protected void findViewsById(View rootView) {

    }

    @Override
    protected int setLayoutRes() {
        return 0;
    }

    @Override
    public void assingDatasAndEvents(Activity activity, @Nullable Bean1 bean) {

    }


}
