package com.itcast.yb.packelves.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.adapter.SectionAdapter;
import com.itcast.yb.packelves.adapter.entity.MySectionEntity;
import com.itcast.yb.packelves.bean.OpenServiceInfoBean;
import com.itcast.yb.packelves.bean.SectionBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.orhanobut.logger.Logger;

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

public class KaiFuFragment extends BaseFragment {
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private static final String METHOD = "getJtkaifu";
    private ArrayList<OpenServiceInfoBean.EntityInfo> mInfo;
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
        mInfo = body.info;
        Logger.d(mInfo.size());
        //添加分组头数据
        List<String> headList = addHeaderDatas(mInfo);
        if(headList != null) {
            mDatas = new ArrayList<>();
            for (int i=0; i<headList.size();i++) {
                String headerTime = headList.get(i);
                mDatas.add(new MySectionEntity(true,headerTime));
                for (int j=0;j<mInfo.size();j++) {
                    OpenServiceInfoBean.EntityInfo info = mInfo.get(j);
                    String addtime = info.addtime;
                    if(addtime.equals(headerTime)) {
                        SectionBean bean = new SectionBean();
                        bean.gname = info.gname;
                        bean.iconurl = info.iconurl;
                        bean.area = info.area;
                        bean.linkurl = info.linkurl;
                        bean.operators = info.operators;
                        bean.addtime = info.addtime;
                        mDatas.add(new MySectionEntity(bean));
                    }
                }
            }
            mAdapter = new SectionAdapter(R.layout.recycler_item_openservice,
                    R.layout.recyler_item_head_openserver, mDatas);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    //添加分组头的数据信息
    private List<String> addHeaderDatas(ArrayList<OpenServiceInfoBean.EntityInfo> info) {
        List<String> headList = new ArrayList<>();
        for (int i=0;i<mInfo.size();i++) {
            String addtime = mInfo.get(i).addtime;
            if(!headList.contains(addtime)) {
                headList.add(addtime);
            }else {
                continue;
            }
        }
        return headList;
    }

}
