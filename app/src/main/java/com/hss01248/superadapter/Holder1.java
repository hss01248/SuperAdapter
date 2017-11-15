package com.hss01248.superadapter;

import android.app.Activity;
import android.widget.TextView;

import com.hss01248.adapter.SuperLvHolder;

import butterknife.BindView;

/**
 * Created by huangshuisheng on 2017/11/15.
 */

public class Holder1 extends SuperLvHolder<Bean1> {
    @BindView(R.id.tv_text)
    TextView tvText;

    public Holder1(Activity context) {
        super(context);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.holder_demo_list;
    }

    @Override
    public void assingDatasAndEvents(Activity context, Bean1 bean) {
        tvText.setText(bean.txt);

    }
}
