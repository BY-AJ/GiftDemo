package com.itcast.yb.packelves.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yb on 2017/10/31.
 */

public class OpenServiceInfoBean {

    /**
     * info : [{"id":1509003208,"iconurl":"/allimgs/img_iapp/201703/_1489124054272.jpg","gname":"不服小天王","openstate":1,"opentype":2,"score":5,"linkurl":"11-04 10:00","istop":0,"colors":0,"platform":"1,2","operators":"乐趣网","state":1,"addtime":"2017-11-04","starttime":"2017-11-04 10:00","keyword":" ","area":"双线84服","uid":"402881d2568bf8f401568d605e9800a0","gid":"1484205897","indexpy":"0","isdel":1,"openflag":3,"vtypeimage":"<i class='android'><\/i><i class='ios'><\/i>"},{"id":1509611049,"iconurl":"/allimgs/img_iapp/201703/_1489124054272.jpg","gname":"不服小天王","openstate":1,"opentype":2,"score":5,"linkurl":"11-07 10:00","istop":0,"colors":0,"platform":"1,2","operators":"乐趣网","state":1,"addtime":"2017-11-07","starttime":"2017-11-07 10:00","keyword":" ","area":"双线85服","uid":"402881d2568bf8f401568d605e9800a0","gid":"1484205897","indexpy":"0","isdel":1,"openflag":3,"vtypeimage":"<i class='android'><\/i><i class='ios'><\/i>"},{"id":1509611073,"iconurl":"/allimgs/img_iapp/201703/_1489124054272.jpg","gname":"不服小天王","openstate":1,"opentype":2,"score":5,"linkurl":"11-10 10:00","istop":0,"colors":0,"platform":"1,2","operators":"乐趣网","state":1,"addtime":"2017-11-10","starttime":"2017-11-10 10:00","keyword":" ","area":"双线86服","uid":"402881d2568bf8f401568d605e9800a0","gid":"1484205897","indexpy":"0","isdel":1,"openflag":3,"vtypeimage":"<i class='android'><\/i><i class='ios'><\/i>"}]
     */

    private List<InfoEntity> info;

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public static class InfoEntity implements Serializable{
        /**
         * id : 1509003208
         * iconurl : /allimgs/img_iapp/201703/_1489124054272.jpg
         * gname : 不服小天王
         * openstate : 1
         * opentype : 2
         * score : 5.0
         * linkurl : 11-04 10:00
         * istop : 0
         * colors : 0
         * platform : 1,2
         * operators : 乐趣网
         * state : 1
         * addtime : 2017-11-04
         * starttime : 2017-11-04 10:00
         * keyword :
         * area : 双线84服
         * uid : 402881d2568bf8f401568d605e9800a0
         * gid : 1484205897
         * indexpy : 0
         * isdel : 1
         * openflag : 3
         * vtypeimage : <i class='android'></i><i class='ios'></i>
         */

        private int id;
        private String iconurl;
        private String gname;
        private int openstate;
        private int opentype;
        private double score;
        private String linkurl;
        private int istop;
        private int colors;
        private String platform;
        private String operators;
        private int state;
        private String addtime;
        private String starttime;
        private String keyword;
        private String area;
        private String uid;
        private String gid;
        private String indexpy;
        private int isdel;
        private int openflag;
        private String vtypeimage;

        public void setId(int id) {
            this.id = id;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public void setOpenstate(int openstate) {
            this.openstate = openstate;
        }

        public void setOpentype(int opentype) {
            this.opentype = opentype;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public void setLinkurl(String linkurl) {
            this.linkurl = linkurl;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }

        public void setColors(int colors) {
            this.colors = colors;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public void setOperators(String operators) {
            this.operators = operators;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public void setIndexpy(String indexpy) {
            this.indexpy = indexpy;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public void setOpenflag(int openflag) {
            this.openflag = openflag;
        }

        public void setVtypeimage(String vtypeimage) {
            this.vtypeimage = vtypeimage;
        }

        public int getId() {
            return id;
        }

        public String getIconurl() {
            return iconurl;
        }

        public String getGname() {
            return gname;
        }

        public int getOpenstate() {
            return openstate;
        }

        public int getOpentype() {
            return opentype;
        }

        public double getScore() {
            return score;
        }

        public String getLinkurl() {
            return linkurl;
        }

        public int getIstop() {
            return istop;
        }

        public int getColors() {
            return colors;
        }

        public String getPlatform() {
            return platform;
        }

        public String getOperators() {
            return operators;
        }

        public int getState() {
            return state;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getStarttime() {
            return starttime;
        }

        public String getKeyword() {
            return keyword;
        }

        public String getArea() {
            return area;
        }

        public String getUid() {
            return uid;
        }

        public String getGid() {
            return gid;
        }

        public String getIndexpy() {
            return indexpy;
        }

        public int getIsdel() {
            return isdel;
        }

        public int getOpenflag() {
            return openflag;
        }

        public String getVtypeimage() {
            return vtypeimage;
        }
    }
}
