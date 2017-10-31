package com.itcast.yb.packelves.adapter.entity;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.itcast.yb.packelves.bean.SectionBean;

/**
 * Created by yb on 2017/10/31.
 */

public class MySectionEntity extends SectionEntity<SectionBean>{
    public MySectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySectionEntity(SectionBean sectionBean) {
        super(sectionBean);
    }
}
