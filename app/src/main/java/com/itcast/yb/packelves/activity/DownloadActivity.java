package com.itcast.yb.packelves.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.DownloadInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.itcast.yb.packelves.service.DownloadInfo;
import com.itcast.yb.packelves.service.DownloadManager;
import com.itcast.yb.packelves.service.DownloadService;
import com.itcast.yb.packelves.utils.UIUtils;
import com.itcast.yb.packelves.view.ProgressHorizontal;
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

public class DownloadActivity extends BaseActivity implements DownloadManager.DownloadObserver,View.OnClickListener{
    @BindView(R.id.tv_details_title) TextView tvDetailsTitle;//标题
    @BindView(R.id.iv_details_share) ImageView ivDetailsShare;//分享
    @BindView(R.id.iv_download_icon) ImageView ivDownloadIcon;//logo
    @BindView(R.id.tv_download_name) TextView tvDownloadName;//名字
    @BindView(R.id.tv_download_type) TextView tvDownloadType;//类型
    @BindView(R.id.tv_download_size) TextView tvDownloadSize;//大小
    @BindView(R.id.tv_download_des ) TextView tvDownloadDes;//描述
    @BindView(R.id.ll_root) LinearLayout llRoot;
    @BindView(R.id.rl_root) RelativeLayout rlRoot;
    @BindView(R.id.fl_progress) FrameLayout flProgress;
    private Button btnDownload;
    private ProgressHorizontal pbProgress;
    private int mCurrentState;
    private float mProgress;

    private int mAppid;
    private ArrayList<DownloadInfoBean.ImageInfo> mImageDatas;
    private DownloadInfoBean.AppInfo mAppDatas;
    private ImageView[] mPics;
    private String mTitle;
    private DownloadService.DownloadBinder mBinder;
    private DownloadManager mDM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        btnDownload = (Button) findViewById(R.id.btn_download);
        initBasic();//初始化基本信息
        initView();
        initData();
    }

    private void initView() {
        pbProgress = new ProgressHorizontal(UIUtils.getContext());
        pbProgress.setProgressBackgroundResource(R.drawable.progress_bg);// 进度条背景图片
        pbProgress.setProgressResource(R.drawable.progress_normal);// 进度条图片
        pbProgress.setProgressTextColor(Color.WHITE);// 进度文字颜色
        pbProgress.setProgressTextSize(UIUtils.dip2px(18));// 进度文字大小

        // 宽高填充父窗体
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        // 给帧布局添加自定义进度条
        flProgress.addView(pbProgress, params);

        flProgress.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
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

    /**
     * 请求网络获取数据
     */
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

    /**
     * 解析数据
     */
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

            // 判断当前应用是否下载过
            DownloadInfo downloadInfo = mDM.getDownloadInfo(mAppDatas);
            if (downloadInfo != null) {
                // 之前下载过
                mCurrentState = downloadInfo.currentState;
                mProgress = downloadInfo.getProgress();
            } else {
                // 没有下载过
                mCurrentState = DownloadManager.STATE_UNDO;
                mProgress = 0;
            }
            refreshUI(mCurrentState, mProgress);
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

        //注册观察者
        mDM = DownloadManager.getInstance();
        mDM.registerObserver(this);
    }

    //返回按钮
    @OnClick(R.id.iv_details_back)
    public void backPreActivity() {
        finish();
    }

    @Override
    public void onDownloadStateChanged(DownloadInfo info) {
        refreshUIOnMainThread(info);
    }

    @Override
    public void onDownloadProgressChanged(DownloadInfo info) {
        refreshUIOnMainThread(info);
    }

    // 主线程更新ui
    private void refreshUIOnMainThread(final DownloadInfo info) {
        // 判断下载对象是否是当前应用
        if(mAppDatas != null) {
            if (mAppDatas.id == info.id) {
                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI(info.currentState, info.getProgress());
                    }
                });
            }
        }
    }

    // 根据当前的下载进度和状态来更新界面
    private void refreshUI(int currentState, float progress) {
        mCurrentState = currentState;
        mProgress = progress;
        switch (currentState) {
            case DownloadManager.STATE_UNDO:// 未下载
                flProgress.setVisibility(View.GONE);
                btnDownload.setVisibility(View.VISIBLE);
                btnDownload.setText("下载");
                break;
            case DownloadManager.STATE_WAITING:// 等待下载
                flProgress.setVisibility(View.GONE);
                btnDownload.setVisibility(View.VISIBLE);
                btnDownload.setText("等待中..");
                break;
            case DownloadManager.STATE_DOWNLOADING:// 正在下载
                flProgress.setVisibility(View.VISIBLE);
                btnDownload.setVisibility(View.GONE);
                pbProgress.setCenterText("");
                pbProgress.setProgress(mProgress);// 设置下载进度
                break;
            case DownloadManager.STATE_PAUSE:// 下载暂停
                flProgress.setVisibility(View.VISIBLE);
                btnDownload.setVisibility(View.GONE);
                pbProgress.setCenterText("暂停");
                pbProgress.setProgress(mProgress);
                Logger.d("暂停界面更新:" + mCurrentState);
                break;
            case DownloadManager.STATE_ERROR:// 下载失败
                flProgress.setVisibility(View.GONE);
                btnDownload.setVisibility(View.VISIBLE);
                btnDownload.setText("下载失败");
                break;
            case DownloadManager.STATE_SUCCESS:// 下载成功
                flProgress.setVisibility(View.GONE);
                btnDownload.setVisibility(View.VISIBLE);
                btnDownload.setText("安装");
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:
            case R.id.fl_progress:
                // 根据当前状态来决定下一步操作
                if (mCurrentState == DownloadManager.STATE_UNDO
                        || mCurrentState == DownloadManager.STATE_ERROR
                        || mCurrentState == DownloadManager.STATE_PAUSE) {
                    mDM.download(mAppDatas);// 开始下载
                } else if (mCurrentState == DownloadManager.STATE_DOWNLOADING
                        || mCurrentState == DownloadManager.STATE_WAITING) {
                    mDM.pause(mAppDatas);// 暂停下载
                } else if (mCurrentState == DownloadManager.STATE_SUCCESS) {
                    mDM.install(mAppDatas);// 开始安装
                }
                break;
            default:
                break;
        }
    }
}
