package com.itcast.yb.packelves.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.KaiCeInfoBean;
import com.itcast.yb.packelves.bean.NewGameInfoBean;
import com.itcast.yb.packelves.utils.HttpUtils;

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
             helper.setText(R.id.second_kaice_gname,item.getGname());
             helper.setText(R.id.second_addtimeAndGettime_txt,item.getAddtime()+item.getLinkurl());
             helper.setText(R.id.second_kaice_operators_txt,item.getOperators());

            ImageView icons = (ImageView) helper.itemView.findViewById(R.id.second_kaice_ImageView);
            Glide.with(helper.itemView.getContext()).
                         load(HttpUtils.BASE_URL+item.getIconurl()).
                         into(icons);
    }


}
