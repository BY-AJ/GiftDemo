package com.itcast.yb.packelves.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.NewGameInfoBean;
import com.itcast.yb.packelves.utils.HttpUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/10/30.
 */
public class NewGameQuickAdapter extends BaseQuickAdapter<NewGameInfoBean.ListEntity,BaseViewHolder> {

    public NewGameQuickAdapter(@Nullable List<NewGameInfoBean.ListEntity> data) {
        super(R.layout.recycler_item_newgame,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewGameInfoBean.ListEntity item) {
            helper.setText(R.id.xinyou_name_item_tv,item.getName());
           ImageView icons = (ImageView) helper.itemView.findViewById(R.id.xinyou_iconl__item_iv);
           ImageView authicons = (ImageView) helper.itemView.findViewById(R.id.xinyou_authord__item_iv);
        Glide.with(helper.itemView.getContext()).
                     load(HttpUtils.BASE_URL+item.getIconurl()).
                     into(icons);
        Glide.with(helper.itemView.getContext()).
                load(HttpUtils.BASE_URL+item.getAuthorimg()).
                into(authicons);
    }
}
