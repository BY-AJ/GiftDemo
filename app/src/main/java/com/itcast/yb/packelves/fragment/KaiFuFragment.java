package com.itcast.yb.packelves.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.activity.KaiCeDetailsActivity;
import com.itcast.yb.packelves.activity.KaiFuDetailsActivity;
import com.itcast.yb.packelves.adapter.SectionAdapter;
import com.itcast.yb.packelves.adapter.entity.MySectionEntity;
import com.itcast.yb.packelves.bean.OpenServiceInfoBean;
import com.itcast.yb.packelves.bean.SectionBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yb on 2017/10/30.
 */

public class KaiFuFragment extends BaseFragment {
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private static final String METHOD = "getJtkaifu";
    private ArrayList<OpenServiceInfoBean.InfoEntity> mInfo;
    private List<MySectionEntity> mDatas;
    private SectionAdapter mAdapter;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_kaifu, null);
        ButterKnife.bind(this,view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void initData() {
        Call<OpenServiceInfoBean> call = RequestNetwork.getOpenServiceClient(METHOD);
        call.enqueue(new Callback<OpenServiceInfoBean>() {
            @Override
            public void onResponse(Call<OpenServiceInfoBean> call, Response<OpenServiceInfoBean> response) {
                parseData(response.body());
            }
            @Override
            public void onFailure(Call<OpenServiceInfoBean> call, Throwable t) {
                Toast.makeText(mActivity,"网络连接失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseData(OpenServiceInfoBean body) {
        mInfo = (ArrayList<OpenServiceInfoBean.InfoEntity>) body.getInfo();
        Logger.d(mInfo.size());
        //添加分组头数据
        List<String> headList = addHeaderDatas(mInfo);
        if(headList != null) {
            mDatas = new ArrayList<>();
            for (int i=0; i<headList.size();i++) {
                String headerTime = headList.get(i);
                if(headerTime.equals(getTime())) {
                    mDatas.add(new MySectionEntity(true,headerTime+"  今日开服"));
                }else {
                    mDatas.add(new MySectionEntity(true,headerTime));
                }
                for (int j=0;j<mInfo.size();j++) {
                    OpenServiceInfoBean.InfoEntity info = mInfo.get(j);
                    String addtime = info.getAddtime();
                    if(addtime.equals(headerTime)) {
                        SectionBean bean = new SectionBean();
                        bean.gname = info.getGname();
                        bean.iconurl = info.getIconurl();
                        bean.area = info.getArea();
                        bean.linkurl = info.getLinkurl();
                        bean.operators = info.getOperators();
                        bean.addtime = info.getAddtime();
                        mDatas.add(new MySectionEntity(bean));
                    }
                }
            }
            mAdapter = new SectionAdapter(R.layout.recycler_item_openservice,
                    R.layout.recyler_item_head_openserver, mDatas);
            mRecyclerView.setAdapter(mAdapter);


            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    MySectionEntity mySectionEntity=mDatas.get(position);
                   String area= mySectionEntity.t.area;
                    String addtime= mySectionEntity.t.addtime;
                    String gamename= mySectionEntity.t.gname;
                    OpenServiceInfoBean.InfoEntity kaiFuInfoBean=null;
                    for (int i = 0; i <mInfo.size() ; i++) {
                        OpenServiceInfoBean.InfoEntity entityInfo= (OpenServiceInfoBean.InfoEntity)mInfo.get(i);
                        if(entityInfo.getArea().equals(area)&&entityInfo.getAddtime().equals(addtime)&&entityInfo.getGname().equals(gamename)){
                            kaiFuInfoBean=entityInfo;
                            break;
                        }
                    }

                    Intent intent=new Intent(mActivity, KaiFuDetailsActivity.class);
                    intent.putExtra("details",kaiFuInfoBean);
                    startActivity(intent);
                }
            });
        }
    }

    //添加分组头的数据信息
    private List<String> addHeaderDatas(ArrayList<OpenServiceInfoBean.InfoEntity> info) {
        List<String> headList = new ArrayList<>();
        for (int i=0;i<mInfo.size();i++) {
            String addtime = mInfo.get(i).getAddtime();
            if(!headList.contains(addtime)) {
                headList.add(addtime);
            }else {
                continue;
            }
        }
        return headList;
    }

    //获取当天的时间
    private String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
