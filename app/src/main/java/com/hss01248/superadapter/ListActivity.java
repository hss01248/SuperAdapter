package com.hss01248.superadapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hss01248.adapter.SuperLvAdapter;
import com.hss01248.adapter.SuperLvHolder;
import com.orhanobut.logger.XLogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangshuisheng on 2017/11/15.
 */

public class ListActivity extends Activity{

    int i=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        ListView listView = (ListView) findViewById(R.id.iv_list);

       List datas = new ArrayList<>();

        datas.add(new Bean1("0"));
        datas.add(new Bean1("two"));
        datas.add(new Bean1("3"));
        datas.add(new Bean1("four"));
        datas.add(new Bean1("5"));
        datas.add(new Bean1("six"));

        datas.add(new Bean2("7"));
        datas.add(new Bean1("eight"));
        datas.add(new Bean1("9"));
        datas.add(new Bean1("ten"));
        datas.add(new Bean2("11"));
        datas.add(new Bean1("twelve"));

        datas.add(new Bean1("13"));
        datas.add(new Bean2("fourteen"));
        datas.add(new Bean1("15"));
        datas.add(new Bean1("sixteen"));
        datas.add(new Bean1("17"));
        datas.add(new Bean1("eighteen"));
        SuperLvAdapter adapter = new SuperLvAdapter<Activity>(this) {
            @Override
            protected SuperLvHolder generateNewHolder(Activity context, int itemViewType, Class clazz, ViewGroup parent) {
                XLogUtil.i("generateCustomViewHolder--"+i);
                i++;
                if(clazz == Bean1.class){
                    return new Holder1(context,parent);
                }else if(clazz ==  Bean2.class){
                    return new Holder2(context,parent);
                }
                return null;
            }
        };
        adapter.addAll(datas);
        listView.setAdapter(adapter);


    }
}
