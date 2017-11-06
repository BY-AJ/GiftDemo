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
import com.itcast.yb.packelves.activity.BeatenDetailsActivity;
import com.itcast.yb.packelves.adapter.FeatureQuickAdapter;
import com.itcast.yb.packelves.bean.FeatureInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.itcast.yb.packelves.utils.ToolUtil;
import com.itcast.yb.packelves.utils.UIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yb on 2017/10/30.
 */

public class BeatenFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.swipe_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_View) RecyclerView mRecyclerView;
    @BindView(R.id.rl_root) RelativeLayout rlRoot;
    @BindView(R.id.btn_loading) Button btnLoading;

    private static final String BEAT_METHOD ="bdxqs";
    private ArrayList<FeatureInfoBean.EntityInfo> mDatas;
    private FeatureQuickAdapter mAdapter;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.fragment_beaten);
        ButterKnife.bind(this,view);

        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.YELLOW,Color.BLACK);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeRefreshLayout.setDistanceToTriggerSync(50);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);

        if(ToolUtil.isNetworkAvailable(mActivity)) {
            rlRoot.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }else {
            mSwipeRefreshLayout.setVisibility(View.GONE);
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
    public void initData() {
        getDataForServer();
    }

    private void getDataForServer() {
        Call<FeatureInfoBean> call = RequestNetwork.getFeatureClient(BEAT_METHOD);
        call.enqueue(new Callback<FeatureInfoBean>() {
            @Override
            public void onResponse(Call<FeatureInfoBean> call, Response<FeatureInfoBean> response) {
                rlRoot.setVisibility(View.GONE);
                mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                FeatureInfoBean body = response.body();
                parseData(body);
            }
            @Override
            public void onFailure(Call<FeatureInfoBean> call, Throwable t) {
                Logger.d(t.getMessage());
            }
        });
    }

    private void parseData(FeatureInfoBean body) {
        mDatas = body.list;
        mAdapter = new FeatureQuickAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //设置条目的点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FeatureInfoBean.EntityInfo entityInfo = mDatas.get(position);

                Intent intent = new Intent(mActivity, BeatenDetailsActivity.class);
                intent.putExtra("details",entityInfo);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        },4000);
    }
}
