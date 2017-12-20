package com.hss01248.superadapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.hss01248.adapter.SuperLvHolder;

/**
 * Created by huangshuisheng on 2017/11/15.
 */

public class Holder1 extends SuperLvHolder<Bean1, Activity> {


    private TextView tvText;

    public Holder1(Activity context) {
        super(context);
    }

    @Override
    protected void findViewsById(View rootView) {
        tvText = (TextView) rootView.findViewById(R.id.tv_text);
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.holder_demo_list;
    }

    @Override
    public void assingDatasAndEvents(Activity context, Bean1 bean) {
        tvText.setText(bean.toString());
    }


}
