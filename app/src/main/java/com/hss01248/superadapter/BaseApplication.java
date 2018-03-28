package com.hss01248.superadapter;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.hss01248.adapter.IBindView;
import com.hss01248.adapter.SuperHolderInitor;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/26.
 */
public class BaseApplication extends Application {

  public static   Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        SuperHolderInitor.init(new IBindView() {
            @Override
            public void bind(Object holder, View rootView) {
                ButterKnife.bind(holder,rootView);
            }
        });
    }
}
