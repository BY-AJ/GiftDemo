package com.itcast.yb.packelves.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.activity.NewGameDetailsActivity;
import com.itcast.yb.packelves.adapter.NewGameQuickAdapter;
import com.itcast.yb.packelves.bean.NewGameInfoBean;
import com.itcast.yb.packelves.utils.HttpUtils;
import com.itcast.yb.packelves.utils.ToolUtil;
import com.itcast.yb.packelves.utils.UIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yb on 2017/10/30.
 */

public class NewGameFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private int pageno=0;
    private List<NewGameInfoBean.ListEntity> mDatas=new ArrayList<>();
    private NewGameQuickAdapter mAdapter;

    @BindView(R.id.swipe_refresh1) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_View1) RecyclerView recyclerView;
    @BindView(R.id.rl_root) RelativeLayout rlRoot;
    @BindView(R.id.btn_loading) Button btnLoading;

    @Override
    public void initData() {
        getDataForServer();
    }

    @Override
    public View initView() {
        View view= UIUtils.inflate(R.layout.fragment_newgame);
        ButterKnife.bind(this,view);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.YELLOW,Color.BLACK);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        swipeRefreshLayout.setDistanceToTriggerSync(50);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        if(ToolUtil.isNetworkAvailable(mActivity)) {
            rlRoot.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }else {
            swipeRefreshLayout.setVisibility(View.GONE);
            rlRoot.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @OnClick(R.id.btn_loading)
    public void reload() {
        btnLoading.setText("正在加载中...");
        getDataForServer();
    }

    @Override
    public void onRefresh() {
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },4000);
    }

    private void getDataForServer() {
        HttpUtils.creat().querGift(pageno).enqueue(new Callback<NewGameInfoBean>() {
            @Override
            public void onResponse(Call<NewGameInfoBean> call, Response<NewGameInfoBean> response) {
                rlRoot.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.VISIBLE);
                parseData(response.body());
            }
            @Override
            public void onFailure(Call<NewGameInfoBean> call, Throwable t) {
                Logger.d(t.getMessage());
            }
        });
    }

    private void parseData(NewGameInfoBean body) {
        mDatas = body.getList();
        mAdapter = new NewGameQuickAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewGameInfoBean.ListEntity entityInfo = mDatas.get(position);
                Intent intent = new Intent(mActivity, NewGameDetailsActivity.class);
                intent.putExtra("details",entityInfo);
                startActivity(intent);
            }
        });
    }
}
