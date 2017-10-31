package com.itcast.yb.packelves.bean;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/10/31.
 */
public class KaiCeInfoBean {

    public ArrayList<InfoEntity> info;

    public static class InfoEntity {
        public String addtime;
        public String gname;
        public String iconurl;
        public String operators;
    }
}
