package com.itcast.yb.packelves.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itcast.yb.packelves.fragment.BaseFragment;
import com.itcast.yb.packelves.fragment.FragmentFactory;

/**
 * Created by yb on 2017/10/29.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter{

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = FragmentFactory.createFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
