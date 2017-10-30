package com.itcast.yb.packelves.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itcast.yb.packelves.bean.GiftInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.itcast.yb.packelves.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by yb on 2017/10/29.
 */

public class MyCarouselAdapter extends PagerAdapter{
    private ArrayList<GiftInfoBean.AdvertiseInfo> data;

    public MyCarouselAdapter(ArrayList<GiftInfoBean.AdvertiseInfo> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % data.size();
        ImageView view = new ImageView(UIUtils.getContext());
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(UIUtils.getContext())
                .load(RequestNetwork.SERVER_URL+data.get(position).iconurl)
                .into(view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }
}
