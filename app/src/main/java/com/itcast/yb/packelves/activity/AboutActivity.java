package com.itcast.yb.packelves.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yb on 2017/10/28.
 */

public class AboutActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }
}
