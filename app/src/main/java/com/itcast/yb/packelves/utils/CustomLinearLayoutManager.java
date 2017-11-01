package com.itcast.yb.packelves.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * 自定义线性管理器禁止滑动类
 * Created by lenovo on 2017/11/1.
 */
public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}

