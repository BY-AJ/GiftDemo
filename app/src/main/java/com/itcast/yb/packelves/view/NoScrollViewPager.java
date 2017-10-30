package com.itcast.yb.packelves.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yb on 2017/10/29.
 */

public class NoScrollViewPager extends ViewPager{
    public NoScrollViewPager(Context context) {
        this(context,null);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//不拦截子控件的响应事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;//消费当前事件，禁止滑动
    }
}
