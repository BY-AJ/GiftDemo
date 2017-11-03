package com.itcast.yb.packelves.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.KaiCeInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;

import java.util.List;

/**
 * Created by lenovo on 2017/10/30.
 */
public class KaiCeQuickAdapter extends BaseQuickAdapter<KaiCeInfoBean.InfoEntity,BaseViewHolder> {

    public KaiCeQuickAdapter(@Nullable List<KaiCeInfoBean.InfoEntity> data) {
        super(R.layout.fragement_kaice_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KaiCeInfoBean.InfoEntity item) {
        helper.setText(R.id.tv_kaice_title,item.getGname());
        helper.setText(R.id.tv_kaice_store,"运营商:"+item.getOperators());
        helper.setText(R.id.tv_kaice_time,item.getAddtime());
        helper.addOnClickListener(R.id.btn_openserver_cat);

        ImageView icons = (ImageView) helper.itemView.findViewById(R.id.iv_kaice_icon);
        Glide.with(helper.itemView.getContext())
                .load(RequestNetwork.SERVER_URL+item.getIconurl())
                .into(icons);
    }

}
