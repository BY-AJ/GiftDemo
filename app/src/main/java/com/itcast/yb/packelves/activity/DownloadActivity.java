package com.itcast.yb.packelves.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.DownloadInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;

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

public class DownloadActivity extends BaseActivity{
    @BindView(R.id.tv_details_title) TextView tvDetailsTitle;//标题
    @BindView(R.id.iv_details_share) ImageView ivDetailsShare;//分享
    @BindView(R.id.iv_download_icon) ImageView ivDownloadIcon;//logo
    @BindView(R.id.tv_download_name) TextView tvDownloadName;//名字
    @BindView(R.id.tv_download_type) TextView tvDownloadType;//类型
    @BindView(R.id.tv_download_size) TextView tvDownloadSize;//大小
    @BindView(R.id.tv_download_des ) TextView tvDownloadDes;//描述
    @BindView(R.id.ll_root) LinearLayout llRoot;
    @BindView(R.id.rl_root) RelativeLayout rlRoot;

    private int appid;
    private ArrayList<DownloadInfoBean.ImageInfo> mImageDatas;
    private DownloadInfoBean.AppInfo mAppDatas;
    private ImageView[] mPics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Intent intent = getIntent();
        appid = intent.getIntExtra("appid", 0);
        initBasic();
        initData();
    }

    private void initData() {
        mPics = new ImageView[5];//截图ImageView数组
        mPics[0]= (ImageView) findViewById(R.id.iv_pic1);
        mPics[1]= (ImageView) findViewById(R.id.iv_pic2);
        mPics[2]= (ImageView) findViewById(R.id.iv_pic3);
        mPics[3]= (ImageView) findViewById(R.id.iv_pic4);
        mPics[4]= (ImageView) findViewById(R.id.iv_pic5);
        getDataForService(appid);
    }

    private void getDataForService(int appid) {
        Call<DownloadInfoBean> call = RequestNetwork.getDownloadClient(appid);
        call.enqueue(new Callback<DownloadInfoBean>() {
            @Override
            public void onResponse(Call<DownloadInfoBean> call, Response<DownloadInfoBean> response) {
                rlRoot.setVisibility(View.GONE);
                llRoot.setVisibility(View.VISIBLE);
                parseData(response.body());
            }
            @Override
            public void onFailure(Call<DownloadInfoBean> call, Throwable t) {
                Toast.makeText(DownloadActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseData(DownloadInfoBean body) {
        mAppDatas = body.app;
        mImageDatas = body.img;
        ivDetailsShare.setVisibility(View.GONE);
        tvDetailsTitle.setText(mAppDatas.name);
        tvDownloadName.setText(mAppDatas.name);
        tvDownloadType.setText(mAppDatas.typename);
        tvDownloadSize.setText("大小:未知");
        Glide.with(this).load(RequestNetwork.SERVER_URL+mAppDatas.logo)
                .into(ivDownloadIcon);
        tvDownloadDes.setText(mAppDatas.description);

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
    }

    private void initBasic() {
        ButterKnife.bind(this);
        llRoot.setVisibility(View.INVISIBLE);
    }

    //返回按钮
    @OnClick(R.id.iv_details_back)
    public void backPreActivity() {
        finish();
    }
}
