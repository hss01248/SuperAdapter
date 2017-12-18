package com.hss01248.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public abstract class SuperPagerAdapter<A extends Activity> extends PagerAdapter implements Refreshable{

    List datas = new ArrayList();
    List<SuperPagerHolder> mPagerHolders = new ArrayList<>();
    A context;

    public SuperPagerAdapter(A context){
        this.context = context;
    }


    @Override
    public int getCount() {
        Log.e("dd","SuperPagerAdapter.count:"+datas.size());
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SuperPagerHolder holder = null;
        if(position >= mPagerHolders.size()){
            holder = generateNewHolder(context,container,position);
            mPagerHolders.add(holder);
        }else {
            holder = mPagerHolders.get(position);
        }
        if(holder == null){
            holder = generateNewHolder(context,container,position);
            mPagerHolders.add(holder);
        }
        holder.assingDatasAndEvents(context,datas.get(position),position);
        container.addView(holder.rootView);
        return holder.rootView;
    }

    protected abstract SuperPagerHolder generateNewHolder(A context, ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void refresh(List newData) {
        datas.clear();
        datas.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List newData) {
        datas.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public void delete(int position) {
        datas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void add(Object object) {
        datas.add(object);
        notifyDataSetChanged();
    }
}
