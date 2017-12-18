package com.hss01248.superadapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huangshuisheng on 2017/12/18.
 */

public class SplashActivity extends Activity {


    @BindView(R.id.recyvleview)
    Button recyvleview;
    @BindView(R.id.listview)
    Button listview;
    @BindView(R.id.viewpager)
    Button viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.recyvleview, R.id.listview, R.id.viewpager})
    public void onViewClicked(View view) {
        Class clazz = null;
        switch (view.getId()) {
            case R.id.recyvleview:
                clazz = MainActivity.class;
                break;
            case R.id.listview:
                clazz = ListActivity.class;
                break;
            case R.id.viewpager:
                break;
                default:break;
        }

        if(clazz !=null){
            startActivity(new Intent(this,clazz));
        }
    }
}
