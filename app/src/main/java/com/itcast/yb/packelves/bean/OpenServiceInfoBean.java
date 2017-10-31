package com.itcast.yb.packelves.bean;

import java.util.ArrayList;

/**
 * Created by yb on 2017/10/31.
 */

public class OpenServiceInfoBean {

    public ArrayList<EntityInfo> info;

    public class EntityInfo {
        public String addtime;
        public String area;
        public String gname;
        public String iconurl;
        public String linkurl;
        public String operators;
    }
}
