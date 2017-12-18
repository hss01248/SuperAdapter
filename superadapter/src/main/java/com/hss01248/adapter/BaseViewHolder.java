package com.hss01248.adapter;

import android.app.Activity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/1.
 */

public abstract class BaseViewHolder<A extends Activity,D> {

    A activity;
    public View rootView;

    public BaseViewHolder(A activity){
        rootView = View.inflate(activity,setLayoutRes(),null);
        ButterKnife.bind(this,rootView);
    }

    protected abstract int setLayoutRes();

    public abstract void assingDatasAndEvents(D bean);
}
