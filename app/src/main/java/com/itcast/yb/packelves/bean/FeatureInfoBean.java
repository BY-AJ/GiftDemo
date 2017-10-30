package com.itcast.yb.packelves.bean;

import java.util.ArrayList;

/**
 * Created by yb on 2017/10/30.
 */

public class FeatureInfoBean {

    public ArrayList<EntityInfo> list;
    public int pageno;

    public class EntityInfo{
        public String addtime;
        public String name;
        public String iconurl;
        public String descs;
    }
}
