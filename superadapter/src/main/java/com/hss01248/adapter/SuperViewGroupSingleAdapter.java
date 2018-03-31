package com.hss01248.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangshuisheng on 2018/3/31.
 * 针对单一item类型的viewgroup
 */

public abstract class SuperViewGroupSingleAdapter<A extends Context> implements IAdapter,Refreshable {

    ViewGroup viewGroup;
    private List datas;
    private A context;

    public SuperViewGroupSingleAdapter(A context){
        this.context = context;
        datas = new ArrayList();
    }

    public void setAdapter(ViewGroup viewGroup){
        this.viewGroup = viewGroup;
    }




    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void refresh(List newData){
        if (newData == null){
            datas.clear();
            notifyDataSetChanged();
            return;
        }
        if (datas == null){
            datas = newData;
            notifyDataSetChanged();
        }else {
            datas.clear();
            datas.addAll(newData);
            notifyDataSetChanged();
        }
    }
    @Override
    public void addAll(List newData){
        if (newData == null){
            return;
        }
        if (datas == null){
            datas = newData;
            notifyDataSetChanged();
        }else {
            datas.addAll(newData);
            notifyDataSetChanged();
        }
    }
    @Override
    public void clear(){
        if (datas != null){
            datas.clear();
            notifyDataSetChanged();
        }
    }
    @Override
    public void delete(int position){
        if (datas != null && position < datas.size()){
            datas.remove(position);
            notifyDataSetChanged();
        }
    }
    @Override
    public List getListData(){
        return datas;
    }

    @Override
    public void notifyDataSetChanged() {
       int viewCount =  viewGroup.getChildCount();
       int dataCount = datas.size();
       if(dataCount ==0){
           //还没有view或者被清空
           for (int i = 0; i<viewCount; i++){
                   View view = viewGroup.getChildAt(i);
                   CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                   viewHolder.onDestory();
           }
           viewGroup.removeAllViews();
           return;
        }
        if(viewCount == 0){
           //有数据,但是没有view
            for (int i = 0; i<dataCount; i++){
                Object obj = datas.get(i);
                CommonViewHolder viewHolder = generateNewHolder(context,i,0,null);
                viewHolder.rootView.setTag(viewHolder);
                viewHolder.assingDatasAndEvents(context,obj,i);
                viewGroup.addView(viewHolder.rootView);
            }
            return;
        }

        //view有,数据也有,且数量一致
        if(viewCount == dataCount){
            for (int i = 0; i<dataCount; i++){
                Object obj = datas.get(i);
                View view = viewGroup.getChildAt(i);
                CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                viewHolder.assingDatasAndEvents(context,obj,i);
            }
            return;
        }
        //view数量<数据数量,需要增加view
        if(viewCount < dataCount){
            for (int i = 0; i<dataCount; i++){
                Object obj = datas.get(i);
                if(i < viewCount){
                    View view = viewGroup.getChildAt(i);
                    CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                    viewHolder.assingDatasAndEvents(context,obj);
                }else {
                    CommonViewHolder viewHolder = generateNewHolder(context,i,0,null);
                    viewHolder.rootView.setTag(viewHolder);
                    viewHolder.assingDatasAndEvents(context,obj,i);
                    viewGroup.addView(viewHolder.rootView);
                }

            }
            return;
        }

        //view数量 > 数据个数,需要移除view
        if(viewCount > dataCount){
            for (int i = 0; i<viewCount; i++){
                if(i < dataCount){
                    Object obj = datas.get(i);
                    View view = viewGroup.getChildAt(i);
                    CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                    viewHolder.assingDatasAndEvents(context,obj,i);
                }else {
                    View view = viewGroup.getChildAt(i);
                    CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                    viewHolder.onDestory();
                    viewGroup.removeViewAt(i);
                }
            }
        }
    }

}
