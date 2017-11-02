package com.itcast.yb.packelves.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.KaiCeDetailsBean;
import com.itcast.yb.packelves.bean.KaiCeInfoBean;
import com.itcast.yb.packelves.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KaiCeDetailsActivity extends AppCompatActivity {
    private KaiCeInfoBean.InfoEntity mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_ce_details);
        //获取开启这个Activity传过来的数据
        Intent intent = getIntent();
        mDatas = (KaiCeInfoBean.InfoEntity)intent.getSerializableExtra("details");
        //初始化基本信息
        initBasic();
    }

    private void initBasic() {
        //请求网络
        getDataForService(mDatas.getGid());
    }


    private void getDataForService(String gid) {
        HttpUtils.creat().queryKaiCeDetails(gid).enqueue(new Callback<KaiCeDetailsBean>() {
            @Override
            public void onResponse(Call<KaiCeDetailsBean> call, Response<KaiCeDetailsBean> response) {
                //解析数据
                parase(response.body());
            }

            @Override
            public void onFailure(Call<KaiCeDetailsBean> call, Throwable t) {

            }
        });
    }

        private void parase(KaiCeDetailsBean body){



        }
}
