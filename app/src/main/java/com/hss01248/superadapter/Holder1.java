package com.hss01248.superadapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hss01248.adapter.SuperLvHolder;

import butterknife.BindView;

/**
 * Created by huangshuisheng on 2017/11/15.
 */

public class Holder1 extends SuperLvHolder<Bean1, Activity> {


    @BindView(R.id.tv_text)
    TextView tvText;

    public Holder1(Activity context, ViewGroup viewGroup) {
        super(context, viewGroup);
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
