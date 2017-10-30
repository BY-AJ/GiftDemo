package com.itcast.yb.packelves.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.GiftInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;

import java.util.List;

/**
 * Created by yb on 2017/10/29.
 */

public class GiftQuickAdapter extends BaseQuickAdapter<GiftInfoBean.EntityInfo,BaseViewHolder>{

    public GiftQuickAdapter(@Nullable List<GiftInfoBean.EntityInfo> data) {
        super(R.layout.recycler_item_gift,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftInfoBean.EntityInfo item) {
        helper.setText(R.id.tv_gift_title,item.gname);
        helper.setText(R.id.tv_gift_des,item.giftname);
        helper.setText(R.id.tv_gift_count,"剩余: "+item.number);
        helper.setText(R.id.tv_gift_time,"时间: "+item.addtime);
        ImageView ivGiftAvatar = (ImageView) helper.itemView.findViewById(R.id.iv_gift_avatar);
        Glide.with(helper.itemView.getContext())
                .load(RequestNetwork.SERVER_URL + item.iconurl)
                .into(ivGiftAvatar);
    }
}
