package com.hss01248.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by huangshuisheng on 2017/12/18.
 */

public abstract class CommonViewHolder<T,A extends Activity> {

    public View rootView;
    public A activity;

    public CommonViewHolder(A context){
        int layoutRes = setLayoutRes();
        this.activity = context;
        if(layoutRes !=0){
            rootView = (ViewGroup) View.inflate(context,setLayoutRes(),null);
        }else {
            rootView = setRootView(context);
            if(rootView ==null){
                throw new RuntimeException("setRootView is null !");
            }
        }

        //ButterKnife.bind(this,rootView);
        findViews();
    }

    protected ViewGroup setRootView(Context context) {
        return null;
    }

    protected abstract int setLayoutRes();

    protected abstract void findViews();

    public  abstract void assingDatasAndEvents(A activity, @Nullable T bean);
}
