package com.hss01248.superadapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hss01248.adapter.SuperRvAdapter;
import com.hss01248.adapter.SuperRvHolder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    RecyclerView mRecyclerView;
    List datas;

    SuperRvAdapter mAdapter;
    public  MainActivity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;


        mRecyclerView = (RecyclerView) findViewById(R.id.lv);
        datas = new ArrayList<>();

        datas.add(new Bean1("0"));
        datas.add("two");
        datas.add(new Bean2("3"));
        datas.add("four");
        datas.add("5");
        datas.add(new Bean1("six"));

        datas.add("7");
        datas.add("eight");
        datas.add("9");
        datas.add("ten");
        datas.add("11");
        datas.add(new Bean2("twelve"));

        datas.add(new Bean1("13"));
        datas.add("fourteen");
        datas.add("15");
        datas.add("sixteen");
        datas.add(new Bean2("17"));
        datas.add("eighteen");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));

        mAdapter = new SuperRvAdapter<MainActivity>( MainActivity.this) {

            @Override
            protected SuperRvHolder generateCoustomViewHolder(ViewGroup parent, int viewType) {
                if(viewType == String.class.hashCode()){
                    return new SuperRvHolder<String,MainActivity>(inflate(parent,R.layout.holder_demo_list_2)) {//匿名子类
                        @Override
                        protected void findViewsById(View rootView) {
                            //mTvText = (TextView) rootView.findViewById(R.id.tv_text);
                        }

                        @Override
                        public void assignDatasAndEvents(MainActivity context, String data) {
                            super.assignDatasAndEvents(context, data);
                            ((TextView)(rootView.findViewById(R.id.tv_text))).setText(data+" string type");
                        }
                    };
                }

                if(viewType == Bean1.class.hashCode()){
                    return new CustomHolder(inflate(parent,R.layout.holder_demo_list));
                }

                if(viewType == Bean2.class.hashCode()){
                    return new CustomHolder2(inflate(parent,R.layout.holder_demo_list));
                }
                return new CustomHolder(inflate(parent,R.layout.holder_demo_list));

            }
        };

        try {
            mAdapter.addAll(datas);
            mRecyclerView.setAdapter(mAdapter);

        }catch (Throwable e){
            e.printStackTrace();
        }

    }


    class CustomHolder extends SuperRvHolder<Bean1,MainActivity> {


        TextView mTvText;

        public CustomHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void findViewsById(View rootView) {
            mTvText = (TextView) rootView.findViewById(R.id.tv_text);
        }


        @Override
        public void assignDatasAndEvents(MainActivity context, Bean1 data) {
            mTvText.setText(data.toString()+"  bean1 type");
        }
    }

    public static class CustomHolder2 extends SuperRvHolder<Bean2,MainActivity> {



        TextView mTvText;

        public CustomHolder2(View itemView) {
            super(itemView);
        }

        @Override
        protected void findViewsById(View rootView) {
            mTvText = (TextView) rootView.findViewById(R.id.tv_text);
        }


        @Override
        public void assignDatasAndEvents(MainActivity context, Bean2 data) {
            mTvText.setText(data.toString() +"  bean2 type");
        }


    }
}
