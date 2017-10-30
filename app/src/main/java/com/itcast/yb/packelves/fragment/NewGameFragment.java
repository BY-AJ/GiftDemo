package com.itcast.yb.packelves.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.adapter.FeatureQuickAdapter;
import com.itcast.yb.packelves.adapter.NewGameQuickAdapter;
import com.itcast.yb.packelves.bean.NewGameInfoBean;
import com.itcast.yb.packelves.utils.HttpUtils;
import com.itcast.yb.packelves.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_View) RecyclerView recyclerView;

    @Override
    public void initData() {
        getDataForServer();

    }

    @Override
    public View initView() {
        View view= UIUtils.inflate(R.layout.fragment_newgame);
        ButterKnife.bind(this,view);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.YELLOW,Color.BLACK);
        swipeRefreshLayout.setProgressBackgroundColor(Color.WHITE);
        swipeRefreshLayout.setDistanceToTriggerSync(50);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        return view;
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
                parseData(response.body());

            }

            @Override
            public void onFailure(Call<NewGameInfoBean> call, Throwable t) {
                Toast.makeText(mActivity,"网络连接失败",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void parseData(NewGameInfoBean body) {
        mDatas = body.getList();
        mAdapter = new NewGameQuickAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);
    }
}
