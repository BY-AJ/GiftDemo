package com.itcast.yb.packelves.adapter.entity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.network.RequestNetwork;

import java.util.List;

/**
 * Created by yb on 2017/10/31.
 */

public class HotSection2Adapter extends BaseSectionQuickAdapter<MyHotSectionEntity,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public HotSection2Adapter(int layoutResId, int sectionHeadResId, List<MyHotSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    //加载分组头的数据
    @Override
    protected void convertHead(BaseViewHolder helper, MyHotSectionEntity item) {
        helper.setText(R.id.tv_head_title,item.header);
    }

    //加载条目的数据
    @Override
    protected void convert(BaseViewHolder helper, MyHotSectionEntity item) {
        helper.setText(R.id.tv_hot_tj_name_title,item.t.name);
        ImageView ivIcon = (ImageView) helper.itemView.findViewById(R.id.iv_openserver_icon);
        Glide.with(helper.itemView.getContext())
                .load(RequestNetwork.SERVER_URL+item.t.logo)
                .into(ivIcon);
    }
}
