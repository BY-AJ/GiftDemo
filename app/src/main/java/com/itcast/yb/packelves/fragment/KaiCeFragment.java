package com.itcast.yb.packelves.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.activity.KaiCeDetailsActivity;
import com.itcast.yb.packelves.adapter.KaiCeQuickAdapter;
import com.itcast.yb.packelves.bean.KaiCeInfoBean;
import com.itcast.yb.packelves.utils.HttpUtils;
import com.itcast.yb.packelves.utils.UIUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by yb on 2017/10/30.
 */

public class KaiCeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private int pageno=0;
    private ArrayList<KaiCeInfoBean.InfoEntity> mDatas;
    private KaiCeQuickAdapter mAdapter;

    @BindView(R.id.swipe_refresh) SwipeRefreshLayout mswipeRefreshLayout;
    @BindView(R.id.recycler_View) RecyclerView mrecyclerView;

    @Override
    public View initView() {
        View view=UIUtils.inflate(R.layout.fragment_kaice);
        ButterKnife.bind(this,view);
        mswipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.YELLOW,Color.BLACK);
        mswipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mswipeRefreshLayout.setDistanceToTriggerSync(50);
        mswipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mrecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void initData() {
        getDataForServer();
    }

    private void getDataForServer(){
        HttpUtils.creat().queryKaiCe().enqueue(new Callback<KaiCeInfoBean>() {
            @Override
            public void onResponse(Call<KaiCeInfoBean> call, Response<KaiCeInfoBean> response) {
                parseData(response.body());
            }

            @Override
            public void onFailure(Call<KaiCeInfoBean> call, Throwable t) {
                Toast.makeText(mActivity,"网络连接失败",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void parseData(KaiCeInfoBean body) {
        mDatas = (ArrayList<KaiCeInfoBean.InfoEntity>) body.getInfo();
        mAdapter = new KaiCeQuickAdapter(mDatas);
        mrecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KaiCeInfoBean.InfoEntity kaiCeInfoBean=(KaiCeInfoBean.InfoEntity)mDatas.get(position);
                Intent intent=new Intent(mActivity, KaiCeDetailsActivity.class);
                intent.putExtra("details",kaiCeInfoBean);
                startActivity(intent);

            }
        });

            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    KaiCeInfoBean.InfoEntity kaiCeInfoBean=(KaiCeInfoBean.InfoEntity)mDatas.get(position);
                    Intent intent=new Intent(mActivity, KaiCeDetailsActivity.class);
                    intent.putExtra("details",kaiCeInfoBean);
                    startActivity(intent);
                }
            });



    }

    @Override
    public void onRefresh() {
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mswipeRefreshLayout.setRefreshing(false);
            }
        },4000);
    }
}
