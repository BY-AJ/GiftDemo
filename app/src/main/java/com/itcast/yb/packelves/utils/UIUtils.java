package com.itcast.yb.packelves.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.itcast.yb.packelves.global.PackElvesApplication;

/**
 * Created by yb on 2017/10/29.
 */

public class UIUtils {
    public static Context getContext() {
        return PackElvesApplication.getContext();
    }

    public static View inflate(int id) {
        return View.inflate(getContext(),id,null);
    }

    public static Handler getHandler() {
        return  PackElvesApplication.getHandler();
    }

    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density*dip);
    }

    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px/density;
    }
}
