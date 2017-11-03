package com.itcast.yb.packelves.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.adapter.GameDetailsAdapter;
import com.itcast.yb.packelves.bean.GameDetailsBean;
import com.itcast.yb.packelves.bean.NewGameInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yb on 2017/11/3.
 */

public class NewGameDetailsActivity extends BaseActivity{

    @BindView(R.id.tv_details_title) TextView tvDetailsTitle;
    @BindView(R.id.iv_details_share) ImageView ivDetailsShare;
    @BindView(R.id.iv_newgame_logo) ImageView ivNesgameLogo;
    @BindView(R.id.civ_author) ImageView civAuthor;
    @BindView(R.id.tv_newgame_des) TextView tvNewgameDes;
    @BindView(R.id.recycler_newgame) RecyclerView mRecyclerView;

    private NewGameInfoBean.ListEntity mDatas;
    private ArrayList<GameDetailsBean.EntityInfo> mListDatas;
    private GameDetailsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        initBasic();
        initData();
    }

    private void initData() {
        getDataForServer(mDatas.getId());
    }

    private void getDataForServer(int id) {
        Call<GameDetailsBean> call = RequestNetwork.getNewGameClient(id);
        call.enqueue(new Callback<GameDetailsBean>() {
            @Override
            public void onResponse(Call<GameDetailsBean> call, Response<GameDetailsBean> response) {
                GameDetailsBean body = response.body();
                if(body != null) {
                    parseData(body);
                }
            }
            @Override
            public void onFailure(Call<GameDetailsBean> call, Throwable t) {
                Logger.d(t.getMessage());
            }
        });
    }

    private void parseData(GameDetailsBean body) {
        mListDatas = body.list;
        mAdapter = new GameDetailsAdapter(mListDatas);
        mRecyclerView.setAdapter(mAdapter);
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    //返回按钮
    @OnClick(R.id.iv_details_back)
    public void backPreActivity() {
        finish();
    }
}
