package com.itcast.yb.packelves.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.adapter.WeekDetailsAdapter;
import com.itcast.yb.packelves.bean.FeatureInfoBean;
import com.itcast.yb.packelves.bean.WeekDetailsBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.itcast.yb.packelves.utils.ShareUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 暴打星期三-详情页
 * Created by yb on 2017/11/1.
 */

public class BeatenDetailsActivity extends BaseActivity{
    @BindView(R.id.tv_details_title) TextView tvDetailsTitle;//标题
    @BindView(R.id.iv_details_share) ImageView ivDetailsShare;//分享
    @BindView(R.id.iv_details_icon) ImageView ivDetailsIcon;//背景图
    @BindView(R.id.tv_details_time) TextView tvDetailsTime;//时间
    @BindView(R.id.tv_details_des) TextView tvDetailsDes;//描述
    @BindView(R.id.recycler_details) RecyclerView recyclerDetails;
    private FeatureInfoBean.EntityInfo mDatas;
    private ArrayList<WeekDetailsBean.DetailsInfo> mListDatas;
    private WeekDetailsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaten);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //获取开启这个Activity传过来的数据
        Intent intent = getIntent();
        mDatas = intent.getParcelableExtra("details");
        //初始化基本信息
        initBasic();
        //数据加载
        initData();
    }

    private void initData() {
        //请求网络
        getDataForService(mDatas.sid);
    }

    private void getDataForService(int id) {
        Call<WeekDetailsBean> call = RequestNetwork.getWeekDetailsClient(id);
        call.enqueue(new Callback<WeekDetailsBean>() {
            @Override
            public void onResponse(Call<WeekDetailsBean> call, Response<WeekDetailsBean> response) {
                //解析数据
                parseData(response.body());
            }
            @Override
            public void onFailure(Call<WeekDetailsBean> call, Throwable t) {
                Toast.makeText(BeatenDetailsActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseData(WeekDetailsBean body) {
        mListDatas = body.list;
        mAdapter = new WeekDetailsAdapter(mListDatas);
        recyclerDetails.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WeekDetailsBean.DetailsInfo info = mListDatas.get(position);
                Intent intent = new Intent(BeatenDetailsActivity.this, DownloadActivity.class);
                intent.putExtra("appid",info.appid);
                intent.putExtra("title",info.appname);
                startActivity(intent);
            }
        });
    }

    private void initBasic() {
        ButterKnife.bind(this);
        tvDetailsTitle.setText(mDatas.name);
        tvDetailsTime.setText(mDatas.addtime);
        //修改textview中呈现不同颜色
        SpannableString spannableString = new SpannableString("导读:  " + mDatas.descs);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")),0,3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvDetailsDes.setText(spannableString);
        Glide.with(this)
                .load(RequestNetwork.SERVER_URL + mDatas.iconurl)
                .into(ivDetailsIcon);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerDetails.setLayoutManager(layoutManager);

        ivDetailsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.showShare(BeatenDetailsActivity.this);
            }
        });
    }

    //返回按钮
    @OnClick(R.id.iv_details_back)
    public void backPreActivity() {
        finish();
    }

    //分享按钮
    @OnClick(R.id.iv_details_share)
    public void shareMore(){
        showShare();
    }

    /**
     * 分享功能
     */
    private void showShare() {

    }

}
