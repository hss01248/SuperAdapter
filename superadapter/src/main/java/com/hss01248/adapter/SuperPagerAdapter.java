package com.hss01248.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.hss01248.lib.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2017/12/9.
 */

public abstract class SuperPagerAdapter<A extends Context> extends PagerAdapter implements Refreshable, ILifeCycle {

    List datas = new ArrayList();
    A context;
    private Queue<View> viewPool = new LinkedList<>();
    //View池


    public SuperPagerAdapter(A context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        Log.e("dd", "SuperPagerAdapter.count:" + datas.size());
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /*SuperPagerHolder holder = generateNewHolder(context,container,position);
        container.addView(holder.rootView);*/


        SuperPagerHolder holder = null;
        View view = null;
//当池子中有存货就复用，否则才inflate
        if (viewPool.size() > 0) {
            view = viewPool.poll();
            holder = view.getTag(R.id.zxt_tag_vdb);
        } else {
            holder = generateNewHolder(context, container, position);
            view = holder.rootView;
            view.setTag(R.id.zxt_tag_vdb, holder);
        }
        holder.assingDatasAndEvents(context, datas.get(position), position);
        return view;
    }

    protected abstract SuperPagerHolder generateNewHolder(A context, ViewGroup container,
                                                          int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        //将当前View加入到池子中
        viewPool.offer((View) object);
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

    @Override
    public void onDestory() {

    }
}
