package com.itcast.yb.packelves.adapter.entity;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.itcast.yb.packelves.bean.HotSectionBean;

/**
 * Created by taotao on 2017/11/1.
 */
public class MyHotSectionEntity extends SectionEntity<HotSectionBean> {

    public MyHotSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyHotSectionEntity(HotSectionBean hotSectionBean) {
        super(hotSectionBean);
    }
}
