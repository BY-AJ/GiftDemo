package com.itcast.yb.packelves.bean;

import java.util.ArrayList;

/**
 * Created by yb on 2017/11/1.
 */

public class WeekDetailsBean {

    public ArrayList<DetailsInfo> list;

    public class DetailsInfo {
        public int id;
        public String appname;
        public String appicon;
        public int appid;
    }
}
