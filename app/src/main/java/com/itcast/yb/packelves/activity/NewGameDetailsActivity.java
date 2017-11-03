package com.itcast.yb.packelves.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.NewGameInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yb on 2017/11/3.
 */

public class NewGameDetailsActivity extends BaseActivity{

    @BindView(R.id.tv_details_title) TextView tvDetailsTitle;
    @BindView(R.id.iv_details_share) ImageView ivDetailsShare;
    @BindView(R.id.iv_newgame_logo) ImageView ivNesgameLogo;
    @BindView(R.id.civ_author) ImageView civAuthor;
    @BindView(R.id.tv_newgame_des) TextView tvNewgameDes;

    private NewGameInfoBean.ListEntity mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        initBasic();
        initData();
    }

    private void initData() {

    }

    private void initBasic() {
        Intent intent = getIntent();
        mDatas = intent.getParcelableExtra("details");
        ButterKnife.bind(this);
        tvDetailsTitle.setText(mDatas.getName());
        tvNewgameDes.setText(mDatas.getDescs());
        Glide.with(this)
                .load(RequestNetwork.SERVER_URL+mDatas.getIconurl())
                .into(ivNesgameLogo);
        Glide.with(this)
                .load(RequestNetwork.SERVER_URL+mDatas.getAuthorimg())
                .into(civAuthor);
    }

    //返回按钮
    @OnClick(R.id.iv_details_back)
    public void backPreActivity() {
        finish();
    }
}
