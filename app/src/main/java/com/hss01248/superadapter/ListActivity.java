package com.hss01248.superadapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.hss01248.adapter.SuperLvAdapter;
import com.hss01248.adapter.SuperLvHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangshuisheng on 2017/11/15.
 */

public class ListActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        ListView listView = (ListView) findViewById(R.id.iv_list);

       List datas = new ArrayList<>();

        datas.add(new Bean1("0"));
        datas.add(new Bean1("two"));
        datas.add(new Bean1("3"));
        datas.add(new Bean2("four"));
        datas.add(new Bean1("5"));
        datas.add(new Bean1("six"));

        datas.add(new Bean2("7"));
        datas.add(new Bean1("eight"));
        datas.add(new Bean1("9"));
        datas.add(new Bean1("ten"));
        datas.add(new Bean1("11"));
        datas.add(new Bean2("twelve"));

        datas.add(new Bean1("13"));
        datas.add(new Bean1("fourteen"));
        datas.add(new Bean2("15"));
        datas.add(new Bean1("sixteen"));
        datas.add(new Bean1("17"));
        datas.add(new Bean2("eighteen"));
        SuperLvAdapter adapter = new SuperLvAdapter(this) {

            int TYPE_1 = 1;
            int TYPE_2 = 2;
            @Override
            protected SuperLvHolder generateNewHolder(Activity context, int itemViewType) {
                if(itemViewType == TYPE_1){
                    return new Holder1(context).setType(itemViewType);
                }else if(itemViewType == TYPE_2){
                    return new Holder2(context).setType(itemViewType);
                }
                return null;
            }

            @Override
            public int getItemViewType(int position) {
               if(getListData().get(position) instanceof Bean1) {
                   return TYPE_1;
               }else if(getListData().get(position) instanceof  Bean2){
                   return TYPE_2;
               }else {
                   return 0;
               }
            }
        };
        adapter.addAll(datas);
        listView.setAdapter(adapter);


    }
}
