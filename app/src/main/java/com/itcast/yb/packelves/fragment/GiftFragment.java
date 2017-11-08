package com.itcast.yb.packelves.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itcast.yb.packelves.MainActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.activity.GiftDetailsActivity;
import com.itcast.yb.packelves.adapter.GiftQuickAdapter;
import com.itcast.yb.packelves.adapter.MyCarouselAdapter;
import com.itcast.yb.packelves.bean.GiftInfoBean;
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
 * Created by yb on 2017/10/29.
 */

public class GiftFragment extends BaseFragment {
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_View) RecyclerView mRecyclerView;
    @BindView(R.id.viewpager) ViewPager mViewPager;
    @BindView(R.id.ll_root) LinearLayout llRoot;
    @BindView(R.id.rl_root) RelativeLayout rlRoot;
    @BindView(R.id.btn_loading) Button btnLoading;

    private ArrayList<GiftInfoBean.AdvertiseInfo> mAdDatas;
    private GiftQuickAdapter mAdapter;
    private ArrayList<GiftInfoBean.EntityInfo> mListDatas;
    private int mPreiousPos;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_gift, null);
        ButterKnife.bind(this,view);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.YELLOW,Color.BLACK);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeRefreshLayout.setDistanceToTriggerSync(50);
        mSwipeRefreshLayout.setEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(UIUtils.getContext());
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
        Call<GiftInfoBean> call = RequestNetwork.getGiftClient();
        call.enqueue(new Callback<GiftInfoBean>() {
            @Override
            public void onResponse(Call<GiftInfoBean> call, Response<GiftInfoBean> response) {
                rlRoot.setVisibility(View.GONE);
                mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                GiftInfoBean body = response.body();
                parseData(body);
            }
            @Override
            public void onFailure(Call<GiftInfoBean> call, Throwable t) {
                Logger.d(t.getMessage());
            }
        });
    }

    private void parseData(GiftInfoBean giftInfoBean) {
        mAdDatas = giftInfoBean.ad;
        mListDatas = giftInfoBean.list;
        mAdapter = new GiftQuickAdapter(mListDatas);
        mRecyclerView.setAdapter(mAdapter);
        mViewPager.setAdapter(new MyCarouselAdapter(mAdDatas));

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GiftInfoBean.EntityInfo entityInfo=mListDatas.get(position);
                Intent itent =new Intent(mActivity, GiftDetailsActivity.class);
                itent.putExtra("details",entityInfo);
                startActivity(itent);
            }
        });

        //加载小圆点
        for(int i=0; i<mAdDatas.size();i++) {
            ImageView point = new ImageView(mActivity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i ==0) {
                point.setImageResource(R.drawable.shape_point_red);
            }else {
                point.setImageResource(R.drawable.shape_point_white);
                params.leftMargin = UIUtils.dip2px(6);
                point.setLayoutParams(params);
            }
            llRoot.addView(point);
        }
        //开始轮播
        startRotation();
        //设置页面监听,实现小圆点的滚动
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //页面选中回调该方法
                position = position%mAdDatas.size();
                ImageView point = (ImageView) llRoot.getChildAt(position);
                point.setImageResource(R.drawable.shape_point_red);
                //上个点变为不选中
                ImageView prePoint = (ImageView) llRoot.getChildAt(mPreiousPos);
                prePoint.setImageResource(R.drawable.shape_point_white);
                mPreiousPos = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    //开始轮播
    private void startRotation() {
        //设置页面从mAdDatas.size*10000位置开始滚动
        mViewPager.setCurrentItem(mAdDatas.size()*10000);
        //实现头条的轮播
        new RunnableTask().start();
    }

    private class RunnableTask implements Runnable {
        public void start() {
            //每次开始的时候，清除之前的消息，避免消息的重复
            UIUtils.getHandler().removeCallbacksAndMessages(null);
            UIUtils.getHandler().postDelayed(this,3000);
        }

        @Override
        public void run() {
            int currentItem = mViewPager.getCurrentItem();
            currentItem++;
            mViewPager.setCurrentItem(currentItem);
            UIUtils.getHandler().postDelayed(this,3000);
        }
    }

    @OnClick(R.id.iv_menu)
    public void opneMenu() {
        MainActivity mainActivity = (MainActivity) this.mActivity;
        mainActivity.getDrawerLayout().openDrawer(GravityCompat.START);
    }

}
