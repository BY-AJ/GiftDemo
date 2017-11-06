package com.itcast.yb.packelves.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.WeekDetailsBean;
import com.itcast.yb.packelves.network.RequestNetwork;

import java.util.List;

/**
 * Created by yb on 2017/11/1.
 */

public class WeekDetailsAdapter extends BaseQuickAdapter<WeekDetailsBean.DetailsInfo,BaseViewHolder>{

    public WeekDetailsAdapter(@Nullable List<WeekDetailsBean.DetailsInfo> data) {
        super(R.layout.recycler_item_details_week,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeekDetailsBean.DetailsInfo item) {
        helper.setText(R.id.tv_down_title,item.appname);
        ImageView icon = (ImageView) helper.itemView.findViewById(R.id.iv_down_icon);
        Glide.with(helper.itemView.getContext())
                .load(RequestNetwork.SERVER_URL+item.appicon)
                .into(icon);
    }
}
