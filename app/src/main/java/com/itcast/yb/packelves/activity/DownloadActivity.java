package com.itcast.yb.packelves.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
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
import com.itcast.yb.packelves.service.DownloadService;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yb on 2017/11/2.
 */

public class DownloadActivity extends BaseActivity implements ServiceConnection{
    @BindView(R.id.tv_details_title) TextView tvDetailsTitle;//标题
    @BindView(R.id.iv_details_share) ImageView ivDetailsShare;//分享
    @BindView(R.id.iv_download_icon) ImageView ivDownloadIcon;//logo
    @BindView(R.id.tv_download_name) TextView tvDownloadName;//名字
    @BindView(R.id.tv_download_type) TextView tvDownloadType;//类型
    @BindView(R.id.tv_download_size) TextView tvDownloadSize;//大小
    @BindView(R.id.tv_download_des ) TextView tvDownloadDes;//描述
    @BindView(R.id.ll_root) LinearLayout llRoot;
    @BindView(R.id.rl_root) RelativeLayout rlRoot;
    private Button btnDownload;

    private int mAppid;
    private ArrayList<DownloadInfoBean.ImageInfo> mImageDatas;
    private DownloadInfoBean.AppInfo mAppDatas;
    private ImageView[] mPics;
    private String mTitle;
    private DownloadService.DownloadBinder mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        btnDownload = (Button) findViewById(R.id.btn_download);
        initBasic();//初始化基本信息
        initData();

        Intent intent = new Intent(DownloadActivity.this, DownloadService.class);
        bindService(intent,this,BIND_AUTO_CREATE);
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
        Call<DownloadInfoBean> call = RequestNetwork.getDownloadClient(appid);
        call.enqueue(new Callback<DownloadInfoBean>() {
            @Override
            public void onResponse(Call<DownloadInfoBean> call, Response<DownloadInfoBean> response) {
                rlRoot.setVisibility(View.GONE);
                llRoot.setVisibility(View.VISIBLE);
                parseData(response.body());//解析数据
            }
            @Override
            public void onFailure(Call<DownloadInfoBean> call, Throwable t) {
                Logger.d(t.getMessage());
            }
        });
    }

    private void parseData(DownloadInfoBean body) {
        if(body != null) {
            mAppDatas = body.app;
            mImageDatas = body.img;
            tvDownloadName.setText(mAppDatas.name);
            tvDownloadType.setText(mAppDatas.typename);
            tvDownloadSize.setText("大小:未知");
            Glide.with(this).load(RequestNetwork.SERVER_URL+mAppDatas.logo)
                    .into(ivDownloadIcon);
            tvDownloadDes.setText(mAppDatas.description);
            //判断当前的app是否可以下载
            if(TextUtils.isEmpty(mAppDatas.download_addr)) {
                btnDownload.setText("暂无下载");
                btnDownload.setBackgroundColor(Color.parseColor("#88000000"));
                btnDownload.setEnabled(false);
            }else {
                btnDownload.setText("立即下载");
            }
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
            Logger.d(mAppDatas.download_addr);
            btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBinder.startDownload(mAppDatas.download_addr,mAppDatas.id);
                }
            });
        }

    }

    private void initBasic() {
        Intent intent = getIntent();
        mAppid = intent.getIntExtra("appid",0);
        Logger.d(mAppid+"....");
        mTitle = intent.getStringExtra("title");

        ButterKnife.bind(this);
        llRoot.setVisibility(View.INVISIBLE);
        ivDetailsShare.setVisibility(View.GONE);
        tvDetailsTitle.setText(mTitle);
    }

    //返回按钮
    @OnClick(R.id.iv_details_back)
    public void backPreActivity() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mBinder = (DownloadService.DownloadBinder) service;
        Logger.d("mBinder="+mBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
