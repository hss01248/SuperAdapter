package com.hss01248.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public  abstract   class SuperRvAdapter<A extends Activity> extends RecyclerView.Adapter<SuperRvHolder> implements Refreshable {


    private List datas;
    private A context;
    boolean isListViewFling;
    public static final int TYPE_NULL = 1;

    public boolean isListViewFling() {
        return isListViewFling;
    }

    public void setListViewFling(boolean listViewFling) {
        isListViewFling = listViewFling;
    }



    public SuperRvAdapter(A context){

        this.datas = new ArrayList();
        this.context = context;


    }

    /**
     * 工具方法
     * @param layoutRes
     * @return
     */
    public  View inflate(@LayoutRes int layoutRes){
        return View.inflate(context, layoutRes,null);
    }


    @Override
    public SuperRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return generateCoustomViewHolder(viewType);
    }

    protected abstract SuperRvHolder generateCoustomViewHolder(int viewType);

    @Override
    public void onBindViewHolder(SuperRvHolder holder, int position) {
        holder.assignDatasAndEvents(context,datas.get(position),position,position == getItemCount() -1,isListViewFling,datas,this);
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = datas.get(position);
        if(obj ==null){
            return TYPE_NULL;
        }
        return obj.getClass().hashCode();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void refresh(List newData) {
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
    public void addAll(List newData) {
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
    public void clear() {
        if (datas != null){
            datas.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void delete(int position) {
        if (datas != null && position < getItemCount()){
            datas.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void add(Object object) {
        if (object != null){
            datas.add(object);
            notifyItemInserted(datas.size() -1);
        }

    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    public List getListData(){
        return datas;
    }

    /*public class  ViewHolder extends RecyclerView.ViewHolder {
        public  ViewGroup rootView;
        public ViewHolder(View itemView) {
            super(itemView);
            rootView = (ViewGroup) itemView;
            ButterKnife.bind(this,rootView);

        }

        public void assignDatasAndEvents(Activity context,Object data,int position){
            SuperRclyAdapter.this.assignDatasAndEvents(context,data,position);
        }


    }*/
}
