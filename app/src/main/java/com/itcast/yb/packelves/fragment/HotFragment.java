package com.itcast.yb.packelves.fragment;

import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.itcast.yb.packelves.MainActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.adapter.entity.HotSection2Adapter;
import com.itcast.yb.packelves.adapter.entity.HotSectionAdapter;
import com.itcast.yb.packelves.adapter.entity.MyHotSectionEntity;
import com.itcast.yb.packelves.bean.HotBean;
import com.itcast.yb.packelves.bean.HotSectionBean;
import com.itcast.yb.packelves.utils.HttpUtils;
import com.itcast.yb.packelves.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yb on 2017/10/29.
 */

public class HotFragment extends BaseFragment{
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_search) TextView tvSearch;
    @BindView(R.id.recycler_View_hot) RecyclerView mRecyclerView;
    @BindView(R.id.recycler2_View_hot) RecyclerView mRecyclerView2;

    HotBean.InfoEntity mInfoEntity;
    private List<MyHotSectionEntity> mInfoEntityList=new ArrayList<>();
    private List<MyHotSectionEntity> mInfoEntityList2=new ArrayList<>();

    private HotSectionAdapter mAdapter;
    private HotSection2Adapter mAdapter2;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.fragment_hot);
        ButterKnife.bind(this,view);
        tvTitle.setText("热门游戏");
        tvSearch.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(mActivity,3);
        mRecyclerView2.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void initData() {
        getDataForServer();
    }

    /**
     * 请求服务数据
     */
    private void getDataForServer(){
        HttpUtils.creat().queryHot().enqueue(new Callback<HotBean>() {
            @Override
            public void onResponse(Call<HotBean> call, Response<HotBean> response) {
                mInfoEntity=(HotBean.InfoEntity)response.body().getInfo();
                parseData(mInfoEntity);
            }

            @Override
            public void onFailure(Call<HotBean> call, Throwable t) {

            }
        });


    }

    /**
     *解析数据
     *
     *  */
    private void parseData(HotBean.InfoEntity initData) {
        List<HotBean.InfoEntity.Push1Entity>  push1= initData.getPush1();
        mInfoEntityList.clear();
        mInfoEntityList2.clear();
        mInfoEntityList.add(new MyHotSectionEntity(true,"精品推荐"));
        for (int i = 0; i <push1.size() ; i++) {
            HotSectionBean hotSectionDatas=new HotSectionBean();
            hotSectionDatas.appId=push1.get(i).getAppid();
            hotSectionDatas.clicks=push1.get(i).getClicks()+"";
            hotSectionDatas.logo=push1.get(i).getLogo();
            hotSectionDatas.name=push1.get(i).getName();
            hotSectionDatas.size=push1.get(i).getSize()==null?"大小:不大":"大小:"+push1.get(i).getSize();
            hotSectionDatas.typename="类型:"+push1.get(i).getTypename();
            mInfoEntityList.add(new MyHotSectionEntity(hotSectionDatas));
        }
        mAdapter = new HotSectionAdapter(R.layout.recycler_item_hot_jp,
                R.layout.recyler_item_head_hot_jp, mInfoEntityList);
        mRecyclerView.setAdapter(mAdapter);




        List<HotBean.InfoEntity.Push2Entity>  push2= initData.getPush2();
        mInfoEntityList2.add(new MyHotSectionEntity(true,"热门推荐"));
        for (int i = 0; i <push2.size() ; i++) {
            HotSectionBean hotSectionDatas=new HotSectionBean();
            hotSectionDatas.appId=push2.get(i).getAppid();
            hotSectionDatas.clicks=push2.get(i).getClicks()+"";
            hotSectionDatas.logo=push2.get(i).getLogo();
            hotSectionDatas.name=push2.get(i).getName();
            hotSectionDatas.typename=push2.get(i).getTypename();
            hotSectionDatas.size=push2.get(i).getSize()==null?"大小:不大":"大小:"+push2.get(i).getSize();
            hotSectionDatas.typename="类型:"+push2.get(i).getTypename();
            mInfoEntityList2.add(new MyHotSectionEntity(hotSectionDatas));
        }

        mAdapter2 = new HotSection2Adapter(R.layout.recycler_item_hot_tj,
                R.layout.recyler_item_head_hot_jp, mInfoEntityList2);
        mRecyclerView2.setAdapter(mAdapter2);




    }


    @OnClick(R.id.iv_menu)
    public void opneMenu() {
        MainActivity mainActivity = (MainActivity) this.mActivity;
        mainActivity.getDrawerLayout().openDrawer(GravityCompat.START);
    }
}
