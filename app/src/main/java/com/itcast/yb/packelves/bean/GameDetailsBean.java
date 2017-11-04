package com.itcast.yb.packelves.bean;

import java.util.ArrayList;

/**
 * Created by yb on 2017/11/3.
 */

public class GameDetailsBean {

    public ArrayList<EntityInfo> list;

    public class EntityInfo {
        public int appid;
        public String appname;
        public String descs;
        public String typename;
        public int id;
        public String appsize;
        public String iconurl;
    }
}
