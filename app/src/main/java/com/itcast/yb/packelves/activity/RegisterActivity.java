package com.itcast.yb.packelves.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.btn_register) Button btn_register;
    @BindView(R.id.tv_menu_title) TextView tv_menu_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        ButterKnife.bind(this);
        initBasic();
    }

    private void initBasic() {
        btn_register.setVisibility(View.GONE);
        tv_menu_title.setText("会员注册");
    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }

}
