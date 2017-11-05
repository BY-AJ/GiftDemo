package com.itcast.yb.packelves.global;

import android.content.Context;
import android.os.Handler;

import com.mob.MobApplication;

/**
 * Created by yb on 2017/10/29.
 */

public class PackElvesApplication extends MobApplication {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
    }
    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }
}
