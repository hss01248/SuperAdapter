package com.hss01248.superadapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.hss01248.adapter.SuperLvAdapter;
import com.hss01248.adapter.SuperLvHolder;

import java.util.List;

/**
 * Created by huangshuisheng on 2017/11/15.
 */

public class Holder2 extends SuperLvHolder<Bean2, Activity> {


    private TextView tvText;

    public Holder2(Activity context) {
        super(context);
    }

    @Override
    protected void findViewsById(View rootView) {
        tvText = (TextView) rootView.findViewById(R.id.tv_text);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.holder_demo_list_2;
    }

    @Override
    public void assingDatasAndEvents(Activity context, Bean2 bean) {


    }

    @Override
    public void assingDatasAndEvents(Activity context, Bean2 bean, final int position, boolean isLast,
                                     boolean isListViewFling, List datas, final SuperLvAdapter superAdapter) {
        tvText.setText(bean.toString());
        tvText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                superAdapter.delete(position);

                return true;
            }
        });
    }


}
