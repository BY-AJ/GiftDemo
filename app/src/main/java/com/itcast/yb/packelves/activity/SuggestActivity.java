package com.itcast.yb.packelves.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yb on 2017/10/28.
 */

public class SuggestActivity extends BaseActivity{
    @BindView(R.id.tv_menu_title) TextView tvMenuTitle;
    @BindView(R.id.et_input) EditText etInput;
    @BindView(R.id.et_qq) EditText etQQ;
    @BindView(R.id.btn_register) Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        initBasic();
    }

    private void initBasic() {
        ButterKnife.bind(this);
        tvMenuTitle.setText("我要提建议");
        btnRegister.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_submit)
    public void submit() {

    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }
}
