package com.itcast.yb.packelves.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.DownloadInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.itcast.yb.packelves.utils.HttpUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JinPinDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_details_title) TextView tvDetailsTitle;//标题
    @BindView(R.id.iv_details_share) ImageView ivDetailsShare;//分享
    @BindView(R.id.iv_download_icon) ImageView ivDownloadIcon;//logo
    @BindView(R.id.tv_download_name) TextView tvDownloadName;//名字
    @BindView(R.id.tv_download_type) TextView tvDownloadType;//类型
    @BindView(R.id.tv_download_size) TextView tvDownloadSize;//大小
    @BindView(R.id.tv_download_des ) TextView tvDownloadDes;//描述
    @BindView(R.id.ll_root) LinearLayout llRoot;
    @BindView(R.id.rl_root) RelativeLayout rlRoot;
    @BindView(R.id.btn_download) Button btnDownload;

    private DownloadInfoBean.AppInfo  appEntity;
    private String mTitle;
    private int mAppid;
    private ImageView[] mPics;
    private ArrayList<DownloadInfoBean.ImageInfo> mImageDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //获取开启这个Activity传过来的数据
        Intent intent = getIntent();
        mAppid = intent.getIntExtra("appid",0);
        mTitle = intent.getStringExtra("name");
        //初始化基本信息
        initBasic();
        initData();
    }

    private void initBasic() {
        ButterKnife.bind(this);
        llRoot.setVisibility(View.INVISIBLE);
        ivDetailsShare.setVisibility(View.GONE);
        tvDetailsTitle.setText(mTitle);
    }

    private void initData() {
        mPics = new ImageView[5];//截图ImageView数组
        mPics[0]= (ImageView) findViewById(R.id.iv_pic1);
        mPics[1]= (ImageView) findViewById(R.id.iv_pic2);
        mPics[2]= (ImageView) findViewById(R.id.iv_pic3);
        mPics[3]= (ImageView) findViewById(R.id.iv_pic4);
        mPics[4]= (ImageView) findViewById(R.id.iv_pic5);
        getDataForService(mAppid);
    }


    private void getDataForService(int appid) {
        HttpUtils.creat().queryJinPinDetails(appid).enqueue(new Callback<DownloadInfoBean>() {
            @Override
            public void onResponse(Call<DownloadInfoBean> call, Response<DownloadInfoBean> response) {
                rlRoot.setVisibility(View.GONE);
                llRoot.setVisibility(View.VISIBLE);
                //解析数据
                parase(response.body());
            }

            @Override
            public void onFailure(Call<DownloadInfoBean> call, Throwable t) {

            }
        });
    }

    private void parase(DownloadInfoBean body) {
            appEntity=body.app;
            mImageDatas=body.img;

            //app基本信息 名称 类型 大小  图标
            tvDownloadName.setText(appEntity.name);
            tvDownloadType.setText(appEntity.typename);
            tvDownloadSize.setText("大小:未知");
            Glide.with(this).load(RequestNetwork.SERVER_URL+appEntity.logo)
                    .into(ivDownloadIcon);

            //截图模块加载
            for (int i=0;i<5;i++) {
                if(i<mImageDatas.size()) {
                    Glide.with(this)
                            .load(RequestNetwork.SERVER_URL+mImageDatas.get(i).address)
                            .into(mPics[i]);
                }else {
                    mPics[i].setVisibility(View.GONE);
                }
            }

            //游戏介绍
            tvDownloadDes.setText(appEntity.description);
            //判断当前的app是否可以下载
            if(TextUtils.isEmpty(appEntity.download_addr)) {
                btnDownload.setText("暂无下载");
                btnDownload.setBackgroundColor(Color.parseColor("#88000000"));
                btnDownload.setEnabled(false);
            }else {
                btnDownload.setText("立即下载");
            }
        }

}
