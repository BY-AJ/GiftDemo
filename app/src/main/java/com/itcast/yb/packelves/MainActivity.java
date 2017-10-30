package com.itcast.yb.packelves;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itcast.yb.packelves.activity.AboutActivity;
import com.itcast.yb.packelves.activity.SuggestActivity;
import com.itcast.yb.packelves.adapter.MyViewPagerAdapter;
import com.itcast.yb.packelves.view.NoScrollViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
                    ,RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.viewpager) NoScrollViewPager mViewPager;
    @BindView(R.id.rg_group) RadioGroup mRadioGroup;

    private CircleImageView ivAvatar;
    private TextView tvUserName;
    private int[] mRbIds;
    private FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBasic();
        initData();
    }

    private void initData() {
        mRbIds = new int[]{R.id.rb_gift,R.id.rb_openservice,R.id.rb_hot,R.id.rb_feature};
        mRadioGroup.setOnCheckedChangeListener(MainActivity.this);
        mViewPager.setAdapter(new MyViewPagerAdapter(mFm));
        mViewPager.setOffscreenPageLimit(3);//防止viewpager里面Fragement被销毁;
        mRadioGroup.check(mRbIds[0]);
    }

    private void initBasic() {
        ButterKnife.bind(this);
        mFm = getSupportFragmentManager();
        initNavView();
    }

    private void initNavView() {
        View headerView = mNavigationView.inflateHeaderView(R.layout.navigation_header);
        ivAvatar = (CircleImageView) headerView.findViewById(R.id.iv_avatar);
        tvUserName = (TextView) headerView.findViewById(R.id.tv_username);
        tvUserName.setText("一只奋斗的小菜鸟");

        //将菜单第一项默认选中
        mNavigationView.setCheckedItem(R.id.nav_menu_home);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu_home :
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_menu_gift :
                break;
            case R.id.nav_menu_suggest :
                startActivity(new Intent(MainActivity.this, SuggestActivity.class));
                break;
            case R.id.nav_menu_about :
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i=0;i<mRbIds.length;i++) {
            if(checkedId == mRbIds[i]) {
                mViewPager.setCurrentItem(i,true);
                break;
            }
        }
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

}
