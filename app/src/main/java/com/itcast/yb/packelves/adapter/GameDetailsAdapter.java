package com.itcast.yb.packelves.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.GameDetailsBean;
import com.itcast.yb.packelves.network.RequestNetwork;

import java.util.List;

/**
 * Created by yb on 2017/11/3.
 */

public class GameDetailsAdapter extends BaseQuickAdapter<GameDetailsBean.EntityInfo,BaseViewHolder>{
    public GameDetailsAdapter(@Nullable List<GameDetailsBean.EntityInfo> data) {
        super(R.layout.recycler_item_game,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GameDetailsBean.EntityInfo item) {
        helper.setText(R.id.tv_game_title,item.appname);
        helper.setText(R.id.tv_game_type,item.typename);
        helper.setText(R.id.tv_game_des,item.descs);
        helper.setText(R.id.tv_game_size,item.appsize==null?"大小:未知":"大小:"+item.appsize);
        ImageView icons = (ImageView) helper.itemView.findViewById(R.id.iv_game_icon);
        Glide.with(helper.itemView.getContext())
                .load(RequestNetwork.SERVER_URL+item.iconurl)
                .into(icons);
    }
}
