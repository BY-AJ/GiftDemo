package com.itcast.yb.packelves.fragment;

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
 * Created by yb on 2017/10/29.
 */

public class FeatureFragment extends BaseFragment{

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_search) TextView tvSearch;
    @BindView(R.id.feature_tab_layout) TabLayout mTabLayout;
    @BindView(R.id.feature_viewpager) ViewPager mViewPager;
//    private List<Fragment> fragmentList=new ArrayList<>();
   // private static final String GAME_METHOD ="getWeekll";
   private String[] mTitle = new String[]{"暴打星期三","新游周刊"};

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_feature,null);
        ButterKnife.bind(this,view);
        tvTitle.setText("独家企划");
        tvSearch.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void initData() {

        mViewPager.setAdapter(new MyTabViewPagerAdapter(getFragmentManager(),mTitle,1));
        //给TabLayout添加分割线
        LinearLayout llroot = (LinearLayout) mTabLayout.getChildAt(0);
        llroot.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        llroot.setDividerDrawable(ContextCompat.getDrawable(mActivity,R.drawable.tablayout_divider_vertical));
        mTabLayout.setupWithViewPager(mViewPager);
        //TODO
        // 有点小bug--多此点击其他的radiobutton再点击特色的radiobutton，数据加载不出来。
    }

    @OnClick(R.id.iv_menu)
    public void opneMenu() {
        MainActivity mainActivity = (MainActivity) this.mActivity;
        mainActivity.getDrawerLayout().openDrawer(GravityCompat.START);
    }
}
