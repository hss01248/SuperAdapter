package com.hss01248.superadapter;

/**
 * Created by huangshuisheng on 2017/11/15.
 */

public class Bean1 {
    public String txt;

    public Bean1(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "Bean1{" +
            "txt='" + txt + '\'' +
            '}';
    }
}
