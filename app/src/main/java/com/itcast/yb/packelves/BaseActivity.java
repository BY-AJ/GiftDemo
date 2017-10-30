package com.itcast.yb.packelves;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.itcast.yb.packelves.utils.ActivityCollector;
import com.orhanobut.logger.Logger;

/**
 * Created by yb on 2017/10/27.
 */

public class BaseActivity extends AppCompatActivity{

    private ActivityCollector collector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        collector = ActivityCollector.getActivityCollector();
        collector.addActivity(this);

        Logger.init("YuBing");
        Logger.t(getClass().getSimpleName()).d(getClass().getSimpleName());

        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        collector.removeActivity(this);
    }
}
