package com.hss01248.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/12/9.
 */

public abstract class SuperPagerHolder<T,A extends Context> implements  View.OnAttachStateChangeListener,ILifeCycle{

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
        rootView.addOnAttachStateChangeListener(this);
        if(SuperHolderInitor.getButterKnife() !=null){
            SuperHolderInitor.getButterKnife().bind(this,rootView);
        }
        findViewsById(rootView);
    }

    protected  void findViewsById(View rootView){}

    protected ViewGroup setRootView(Context context) {
        return null;
    }

    protected abstract int setLayoutRes();


    public  abstract void assingDatasAndEvents(A activity, @Nullable T bean, int position);

    @Override
    public void onViewAttachedToWindow(View v) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }

    @Override
    public void onDestory() {

    }
}
