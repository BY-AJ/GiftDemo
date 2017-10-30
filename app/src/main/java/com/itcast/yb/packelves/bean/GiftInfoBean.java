package com.itcast.yb.packelves.bean;

import java.util.ArrayList;

/**
 * Created by yb on 2017/10/29.
 */

public class GiftInfoBean {

    public ArrayList<AdvertiseInfo> ad;
    public ArrayList<EntityInfo> list;

    public class AdvertiseInfo {
        public int id;
        public String title;
        public String iconurl;
        public int appId;
        public String appLogo;
        public String appName;
        public int giftid;
    }

    public class EntityInfo {
        public int id;
        public String iconurl;
        public String addtime;
        public String gname;
        public int number;
        public String giftname;
    }
}
