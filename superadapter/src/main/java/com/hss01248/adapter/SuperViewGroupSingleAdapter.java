package com.hss01248.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangshuisheng on 2018/3/31.
 * 针对单一item类型的viewgroup,根据数据动态生成子view
 */

public abstract class SuperViewGroupSingleAdapter<A extends Context> implements IAdapter<A>, Refreshable {

    ViewGroup viewGroup;
    private List datas;
    private A context;

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    private Object extra;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    OnItemClickListener itemClickListener;

    public SuperViewGroupSingleAdapter(A context) {
        this.context = context;
        datas = new ArrayList();
    }

    public void setAdapter(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }


    //@Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void refresh(List newData) {
        if (newData == null) {
            datas.clear();
            notifyDataSetChanged();
            return;
        }
        if (datas == null) {
            datas = newData;
            notifyDataSetChanged();
        } else {
            datas.clear();
            datas.addAll(newData);
            notifyDataSetChanged();
        }
    }

    @Override
    public void addAll(List newData) {
        if (newData == null) {
            return;
        }
        if (datas == null) {
            datas = newData;
            notifyDataSetChanged();
        } else {
            datas.addAll(newData);
            notifyDataSetChanged();
        }
    }

    @Override
    public void clear() {
        if (datas != null) {
            datas.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void delete(int position) {
        if (datas != null && position < datas.size()) {
            datas.remove(position);
            notifyDataSetChanged();
        }
    }

    //@Override
    public List getListData() {
        return datas;
    }

    @Override
    public void add(Object object) {
        datas.add(object);
        notifyDataSetChanged();

    }

    @Override
    public void notifyDataSetChanged() {
        int viewCount = viewGroup.getChildCount();
        int dataCount = datas.size();
        if (dataCount == 0 && viewCount > 0) {
            //还没有view或者被清空
            for (int i = 0; i < viewCount; i++) {
                View view = viewGroup.getChildAt(i);
                CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                viewHolder.onDestory();
            }
            viewGroup.removeAllViews();
            return;
        }
        if (viewCount == 0) {
            //有数据,但是没有view
            for (int i = 0; i < dataCount; i++) {
                Object obj = datas.get(i);
                CommonViewHolder viewHolder = generateNewHolder(context, i, 0, null);
                viewHolder.rootView.setTag(viewHolder);
                setItemClickListener(obj, viewHolder, i);
                viewHolder.assingDatasAndEvents(context, obj, i,extra,i==dataCount-1,datas,this);
                viewGroup.addView(viewHolder.rootView);
            }
            return;
        }

        //view有,数据也有,且数量一致
        if (viewCount == dataCount) {
            for (int i = 0; i < dataCount; i++) {
                Object obj = datas.get(i);
                View view = viewGroup.getChildAt(i);
                CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                setItemClickListener(obj, viewHolder, i);
                viewHolder.assingDatasAndEvents(context, obj, i,extra,i==dataCount-1,datas,this);
            }
            return;
        }
        //view数量<数据数量,需要增加view
        if (viewCount < dataCount) {
            for (int i = 0; i < dataCount; i++) {
                Object obj = datas.get(i);
                if (i < viewCount) {
                    View view = viewGroup.getChildAt(i);
                    CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                    setItemClickListener(obj, viewHolder, i);
                    viewHolder.assingDatasAndEvents(context, obj,i,extra,i==dataCount-1,datas,this);
                } else {
                    CommonViewHolder viewHolder = generateNewHolder(context, i, 0, null);
                    viewHolder.rootView.setTag(viewHolder);
                    setItemClickListener(obj, viewHolder, i);
                    viewHolder.assingDatasAndEvents(context, obj, i,extra,i==dataCount-1,datas,this);
                    viewGroup.addView(viewHolder.rootView);
                }

            }
            return;
        }

        //view数量 > 数据个数,需要移除view
        if (viewCount > dataCount) {
            for (int i = 0; i < viewCount; i++) {
                if (i < dataCount) {
                    Object obj = datas.get(i);
                    View view = viewGroup.getChildAt(i);
                    CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                    setItemClickListener(obj, viewHolder, i);
                    viewHolder.assingDatasAndEvents(context, obj, i,extra,i==dataCount-1,datas,this);
                } else {
                    View view = viewGroup.getChildAt(i);
                    CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
                    viewGroup.removeViewAt(i);
                    viewHolder.onDestory();
                }
            }
        }
    }

    private void setItemClickListener(final Object obj, final CommonViewHolder viewHolder, final int finalI) {
        if(itemClickListener ==null){
            return;
        }
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener !=null){
                    itemClickListener.onItemClick(finalI,obj,viewHolder);
                }
            }
        });
    }

    public void hideAfter(int position){
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if(i > position){
                view.setVisibility(View.GONE);
            }
        }

    }

    public void showAfter(int position){
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if(i > position){
                view.setVisibility(View.VISIBLE);
            }
        }
    }


    public interface OnItemClickListener{
        void onItemClick(int position, Object obj, CommonViewHolder viewHolder);
    }

}
