package com.itcast.yb.packelves.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.FeatureInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;

import java.util.List;

/**
 * Created by yb on 2017/10/30.
 */

public class FeatureQuickAdapter extends BaseQuickAdapter<FeatureInfoBean.EntityInfo,BaseViewHolder>{
    public FeatureQuickAdapter(@Nullable List<FeatureInfoBean.EntityInfo> data) {
        super(R.layout.recycler_item_beaten,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeatureInfoBean.EntityInfo item) {
        helper.setText(R.id.tv_feature_title,item.name);
        helper.setText(R.id.tv_feature_time,item.addtime);
        ImageView ivPics = (ImageView) helper.itemView.findViewById(R.id.iv_pics);
        Glide.with(helper.itemView.getContext())
                .load(RequestNetwork.SERVER_URL+item.iconurl)
                .into(ivPics);
    }
}
