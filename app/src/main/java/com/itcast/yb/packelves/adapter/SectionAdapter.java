package com.itcast.yb.packelves.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.adapter.entity.MySectionEntity;
import com.itcast.yb.packelves.network.RequestNetwork;

import java.util.List;

/**
 * Created by yb on 2017/10/31.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<MySectionEntity,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List<MySectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    //加载分组头的数据
    @Override
    protected void convertHead(BaseViewHolder helper, MySectionEntity item) {
        helper.setText(R.id.tv_head_time,item.header);
    }

    //加载条目的数据
    @Override
    protected void convert(BaseViewHolder helper, MySectionEntity item) {
        helper.setText(R.id.tv_openserver_title,item.t.gname);
        helper.setText(R.id.tv_openserver_time,item.t.linkurl);
        helper.setText(R.id.tv_openserver_store,"运营商:"+item.t.operators);
        helper.setText(R.id.tv_openserver_area,item.t.area);

        ImageView ivIcon = (ImageView) helper.itemView.findViewById(R.id.iv_openserver_icon);
        Glide.with(helper.itemView.getContext())
                .load(RequestNetwork.SERVER_URL+item.t.iconurl)
                .into(ivIcon);
    }
}
