package com.itcast.yb.packelves.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.RegisterBean;
import com.itcast.yb.packelves.utils.HttpUtils;
import com.orhanobut.logger.Logger;

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
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
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
                String nickname=et_nickname.getText().toString();
                HttpUtils.creat().queryRigester(account,psd,nickname).enqueue(new Callback<RegisterBean>() {
                    @Override
                    public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                        RegisterBean body = response.body();
                        parseData(body);
                    }
                    @Override
                    public void onFailure(Call<RegisterBean> call, Throwable t) {
                    }
                });

            }

    }

    //处理返回的结果
    private void parseData(RegisterBean body) {
        if(body != null) {
            Logger.d(body.returnMsg+"..."+body.flag);
        }else {
            Logger.d("注册失败");
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



//    /**
//     * 获取手机IMEI号
//     */
//    public static String getIMEI(Context context) {
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
//        String imei = telephonyManager.getDeviceId();
//        return imei;
//    }
//
//
//    /**
//     * 获取手机IMEI号
//     */
//    public static String getMid(Context context) {
//        Map<String,String> map=new HashMap<>();
//        map.put("Mac", ToolUtil.getLocalMacAddressFromIp());
//        map.put("deviced_id",getIMEI(context));
//        Gson gson=new Gson();
//        String jsonstr= gson.toJson(map);
//
////        JSONObject jsonObject = new JSONObject();
////        try {
////            jsonObject.put("Mac", ToolUtil.getLocalMacAddressFromIp());
////            jsonObject.put("deviced_id",getIMEI(context));
////            return jsonObject;
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//        return jsonstr;
//    }
}
