package com.hss01248.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/12/9.
 */

public abstract class SuperPagerHolder<T,A extends Activity> {

    public ViewGroup rootView;
    public A activity;

    public SuperPagerHolder(A context){
        int layoutRes = setLayoutRes();
        this.activity = context;
        if(layoutRes !=0){
            rootView = (ViewGroup) View.inflate(context,setLayoutRes(),null);
        }else {
            rootView = setRootView(context);
        }

        //ButterKnife.bind(this,rootView);
        findViews();
    }

    protected ViewGroup setRootView(Context context) {
        return null;
    }

    protected abstract int setLayoutRes();

    protected abstract void findViews();

    public  abstract void assingDatasAndEvents(A activity, @Nullable T bean, int position);
}