package com.itcast.yb.packelves.activity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.LoginBean;
import com.itcast.yb.packelves.utils.HttpUtils;
import com.orhanobut.logger.Logger;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.btn_register) Button btn_register;
    @BindView(R.id.tv_menu_title) TextView tv_menu_title;
    @BindView(R.id.et_account) EditText et_account;
    @BindView(R.id.et_psd) EditText et_psd;
    @BindView(R.id.et_nickname) EditText et_nickname;


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

    @OnClick(R.id.register)
    public void mclick(){
        initData();

    }

    private void initData() {
            if(!isEmpty()){
                //请求 开始注册
                String account= et_account.getText().toString();
                String psd= et_psd.getText().toString();
                String nickname=getMid(RegisterActivity.this);
                String mid=et_nickname.getText().toString();

                HttpUtils.creat().queryRigester(account,psd,nickname,mid).enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        LoginBean body = response.body();
                        parseData(body);
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {

                    }
                });

            }

    }

    //处理返回的结果
    private void parseData(LoginBean body) {
        if(body != null) {
            Logger.d(body.info.nickname+"......"+body.flag+"..."+body.returnMsg);
            if("登录成功".equals(body.returnMsg)) {
//                Intent intent = getIntent();
//                intent.putExtra("name",body.info.nickname);
//                setResult(101,intent);
//                finish();
            }else {
                Toast.makeText(RegisterActivity.this,"账号已存在",Toast.LENGTH_SHORT).show();
//                etAccount.setText("");
//                etPsd.setText("");
            }
        }
    }



    private boolean isEmpty(){
        String account= et_account.getText().toString();
        String psd= et_psd.getText().toString();
        String nickname= et_nickname.getText().toString();
        if(TextUtils.isEmpty(account)||TextUtils.isEmpty(account)||TextUtils.isEmpty(account)){
            return true;
        }else{
            return false;
        }
    }



    /**
     * 获取手机IMEI号
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }


    /**
     * 获取手机IMEI号
     */
    public static String getMid(Context context) {
        Map map=new HashMap();
        map.put("Mac","02:00:00:00:00:00");
        map.put("deviced_id",getIMEI(context));
        Gson gson=new Gson();
        String jsonstr= gson.toJson(map);
        return jsonstr;
    }
}
