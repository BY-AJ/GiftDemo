package com.itcast.yb.packelves.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.LoginBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.itcast.yb.packelves.utils.ToolUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yb on 2017/11/6.
 */

public class LoginActivity extends BaseActivity{
    @BindView(R.id.tv_menu_title) TextView tvMenuTitle;
    @BindView(R.id.et_account) EditText etAccount;
    @BindView(R.id.et_psd) EditText etPsd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initBasic();
    }

    private void initBasic() {
        ButterKnife.bind(this);
        tvMenuTitle.setText("会员登录");
    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.btn_register)
    public  void register(){
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        String account = etAccount.getText().toString();
        String psd = etPsd.getText().toString();
        if(!TextUtils.isEmpty(account)) {
            if(!TextUtils.isEmpty(psd)) {
                if(ToolUtil.isEmail(account) || ToolUtil.isMobileNO(account)) {
                    Call<LoginBean> call = RequestNetwork.getLoginClient(account,psd);
                    call.enqueue(new Callback<LoginBean>() {
                        @Override
                        public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                            LoginBean body = response.body();
                            parseData(body);
                        }
                        @Override
                        public void onFailure(Call<LoginBean> call, Throwable t) {
                            Logger.d(t.getMessage());
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this,"请输入正确的账号",Toast.LENGTH_SHORT);
                }
            }else {
                Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT);
            }
        }else {
            Toast.makeText(LoginActivity.this,"账号不能为空",Toast.LENGTH_SHORT);
        }
    }

    //处理返回的结果
    private void parseData(LoginBean body) {
        if(body != null) {
            Logger.d(body.info.nickname+"......"+body.flag+"..."+body.returnMsg);
        }
        if("登录成功".equals(body.returnMsg)) {
            Intent intent = getIntent();
            intent.putExtra("name",body.info.nickname);
            setResult(101,intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
            etAccount.setText("");
            etPsd.setText("");
        }
    }

}
