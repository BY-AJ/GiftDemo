package com.itcast.yb.packelves.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.itcast.yb.packelves.fragment.FragmentFactory;

/**
 * Created by yb on 2017/10/30.
 */

public class MyTabViewPagerAdapter extends FragmentPagerAdapter{
    private String[] mTitle ;
    private int mflag=0;

    public MyTabViewPagerAdapter(FragmentManager fm,String[] Title,int flag) {
        super(fm);
        mTitle=Title;
        mflag=flag;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    /**
     * mflag==0  开服开测
     *  mflag==1 特色界面
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if(mflag==1){
                return FragmentFactory.createFeatureFg(position);
        }else if(mflag==0){
             return FragmentFactory.createOpengameFg(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Fragment fragment = (Fragment) super.instantiateItem(container, position);
//            mTags  = fragment.getTag();
//        return fragment;
//    }
}
