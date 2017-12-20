package com.hss01248.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public abstract class SuperLvHolder<T,A extends Activity> {
    public View rootView;
    public int type;
    public A activity;

    /*public SuperLvHolder(){

    }*/

    public SuperLvHolder(A context){
        this.activity = context;
        int layoutRes = setLayoutRes();
        if(layoutRes !=0){
            rootView =  View.inflate(context,setLayoutRes(),null);
        }else {
            rootView = setRootView(context);
            if(rootView ==null){
                throw new RuntimeException("setRootView is null !");
            }
        }
        findViewsById(rootView);
    }

    protected abstract void findViewsById(View rootView);

    private View setRootView(A context) {
        return null;
    }

    public SuperLvHolder setType(int type){
        this.type = type;
        return this;
    }

    protected abstract  @LayoutRes  int setLayoutRes();

    /**
     * 一般情况下，实现这个方法就足够了
     * @param activity
     * @param bean
     */
    public  abstract void assingDatasAndEvents(A activity, T bean);

    /**
     * 如果有需要，才实现这个方法
     * @param activity activity实例,用于一些点击事件
     * @param bean 该条目的数据
     * @param position 该条目所在的位置
     * @param isLast 是否为最后一条,有些情况下需要用到
     * @param isListViewFling listview是不是在惯性滑动,备用
     *  @param datas 整个listview对应的数据
     * @param superAdapter adapter对象引用,可用于触发notifydatesetChanged()方法刷新整个listview,比如更改的单选按钮
     */
    public void assingDatasAndEvents(A activity, T bean, int position ,boolean isLast,
                                     boolean isListViewFling,List datas, SuperLvAdapter superAdapter){
        assingDatasAndEvents(activity,bean);
    }
}
