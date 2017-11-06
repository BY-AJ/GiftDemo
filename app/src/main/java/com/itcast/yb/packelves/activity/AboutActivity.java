package com.itcast.yb.packelves.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yb on 2017/10/28.
 */

public class AboutActivity extends BaseActivity{
    @BindView(R.id.btn_register) Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        btnRegister.setVisibility(View.GONE);
    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }
}
