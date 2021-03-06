package com.hss01248.superadapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.hss01248.adapter.SuperLvAdapter;
import com.hss01248.adapter.SuperLvHolder;
import com.linearlistview.LinearListView;
import com.orhanobut.logger.XLogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangshuisheng on 2017/12/18.
 */

public class LinearListLayoutActy extends Activity {




    SuperLvAdapter mAdapter;

    int i = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_linear_listview);
        //ButterKnife.bind(this);
        LinearListView list = (LinearListView) findViewById(R.id.list);


        final List datas = new ArrayList<>();

        datas.add(new Bean1("0"));
        datas.add(new Bean1("two"));
        datas.add(new Bean1("3"));
        datas.add(new Bean2("four"));
        datas.add(new Bean1("5"));
        datas.add(new Bean1("six"));

        datas.add(new Bean1("7"));
        datas.add(new Bean2("eight"));
        datas.add(new Bean1("9"));
        datas.add(new Bean1("ten"));
        datas.add(new Bean1("11"));
        datas.add(new Bean1("twelve"));

        datas.add(new Bean1("13"));
        datas.add(new Bean1("fourteen"));
        datas.add(new Bean2("15"));
        datas.add(new Bean1("sixteen"));
        datas.add(new Bean1("17"));
        datas.add(new Bean1("eighteen"));


        SuperLvAdapter adapter = new SuperLvAdapter<Activity>(this) {
            @Override
            protected SuperLvHolder generateNewHolder(Activity context, int itemViewType, Class beanClass, ViewGroup parent) {
                XLogUtil.i("generateNewHolder---"+(i++));
                if(beanClass == Bean1.class){
                    return new Holder1(context,parent);
                }else if(beanClass == Bean2.class){
                    return new Holder2(context,parent);
                }
                return new Holder1(context,parent);
            }
        };

       /* SuperLvAdapter adapter = new SuperLvAdapter<Activity>(this) {
            @Override
            protected SuperLvHolder generateNewHolder(Activity context, int itemViewType) {
                return new Holder1(context);
            }
        };*/

       /* BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public Object getItem(int position) {
                return datas.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = View.inflate(LinearListLayoutActy.this,R.layout.holder_demo_list,null);
                TextView textView = (TextView) view.findViewById(R.id.tv_text);
                textView.setText(position+"");
                return view;
            }
        };*/
       adapter.addAll(datas);
        list.setAdapter(adapter);
    }
}
