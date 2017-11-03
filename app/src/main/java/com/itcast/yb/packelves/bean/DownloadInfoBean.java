package com.itcast.yb.packelves.bean;

import java.util.ArrayList;

/**
 * Created by yb on 2017/11/2.
 */

public class DownloadInfoBean {

    public ArrayList<ImageInfo> img;

    public AppInfo app;

    public class AppInfo{
        public String description;
        public String download_addr;
        public String name;
        public String typename;
        public String logo;
        public int id;
    }

    public class ImageInfo {
        public String address;
    }
}
