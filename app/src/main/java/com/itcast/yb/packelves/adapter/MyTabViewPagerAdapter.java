package com.itcast.yb.packelves.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itcast.yb.packelves.fragment.BaseFragment;
import com.itcast.yb.packelves.fragment.FragmentFactory;

/**
 * Created by yb on 2017/10/30.
 */

public class MyTabViewPagerAdapter extends FragmentPagerAdapter{

    private String[] mTitle = new String[]{"暴打星期三","新游周刊"};

    public MyTabViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = FragmentFactory.createFeatureFg(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }
}
