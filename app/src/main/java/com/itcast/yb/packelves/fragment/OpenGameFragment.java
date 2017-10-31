package com.itcast.yb.packelves.fragment;

/**
 * Created by taotao on 2017/10/31.
 */

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itcast.yb.packelves.MainActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.adapter.MyTabViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 包括开服和开测
 */

public class OpenGameFragment extends BaseFragment{
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.open_game_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.open_game_viewpager)
    ViewPager mViewPager;
    private String[] mTitle = new String[]{"开服","开测"};

    @Override
    public View initView() {
        View view= LayoutInflater.from(mActivity).inflate(R.layout.fragment_opengame,null);
        ButterKnife.bind(this,view);
        tv_title.setText("开服");
        tvSearch.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void initData() {
        mViewPager.setAdapter(new MyTabViewPagerAdapter(getFragmentManager(),mTitle,0));
        //添加分割线
        LinearLayout linearLayout=(LinearLayout)mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(mActivity,R.drawable.tablayout_divider_vertical));
        mTabLayout.setupWithViewPager(mViewPager);
    }

        @OnClick(R.id.iv_menu)
         public void openmenu(){
            MainActivity mainActivity = (MainActivity) this.mActivity;
            mainActivity.getDrawerLayout().openDrawer(GravityCompat.START);
            }
}
