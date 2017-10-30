package com.itcast.yb.packelves.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by yb on 2017/10/29.
 */

public class PackElvesApplication extends Application{

    private static Context context;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }
}
