package com.itcast.yb.packelves.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Toast;

import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/11/5.
 */
public class LoginActivity extends BaseActivity {
 @BindView(R.id.username_inputlayout)
 TextInputLayout mUsernameLayout;
    @BindView(R.id.password_inputlayout)
    TextInputLayout mPasswordLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

    }

    @OnClick(R.id.btn_login)
        public void onclick(){
        String username = mUsernameLayout.getEditText().getText().toString();
        String password = mPasswordLayout.getEditText().getText().toString();
        if(TextUtils.isEmpty(username)){
            mUsernameLayout.setError("用户名不能为空");
            return;
        }else{
            mUsernameLayout.setErrorEnabled(false);
        }
        if(TextUtils.isEmpty(password)){
            mPasswordLayout.setError("密码不能为空");
            return;
        }else{
            mPasswordLayout.setErrorEnabled(false);
        }
//        Toast.makeText(LoginActivity.this, "Hello "+username, Toast.LENGTH_SHORT).show();
    }


}
