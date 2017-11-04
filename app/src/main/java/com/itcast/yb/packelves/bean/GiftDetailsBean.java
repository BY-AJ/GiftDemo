package com.itcast.yb.packelves.bean;

/**
 * Created by lenovo on 2017/11/4.
 */

/**
 * 礼包详情
 */
public class GiftDetailsBean {

    /**
     * info : {"activityid":"0","addtime":"2017-11-03","agtype":"0","descs":"游戏界面-左上角头像-账号管理-礼包兑换","exchanges":7,"explains":"金币*2000、圣水*2000、木材*2000、L币*50","flag":1,"gcode":"","gid":"1481180322","giftname":"光棍节脱单礼包","gname":"剑与家园","gtype":6,"iconurl":"/allimgs/img_iapp/201612/_1481180262099.png","id":"1509699151","indexpy":"1","integral":0,"isintegral":0,"marks":0,"number":7,"onclicks":0,"operators":"未知","overtime":"2017-11-30","price":10,"ptype":"1,2,","sendtime":"1970-01-01","showimg":"","surplus":0,"turl":"","type":1,"typename":"礼包卡","uid":"ff80808147fd49bc0147fd5240d10006","vtype":"2","vtypeimg":"<i class='android'><\/i><i class='ios'><\/i>","vtypename":"手机客户端","wxcode":"SJ_A7468"}
     */

    private InfoEntity info;

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public static class InfoEntity {
        /**
         * activityid : 0
         * addtime : 2017-11-03
         * agtype : 0
         * descs : 游戏界面-左上角头像-账号管理-礼包兑换
         * exchanges : 7
         * explains : 金币*2000、圣水*2000、木材*2000、L币*50
         * flag : 1
         * gcode :
         * gid : 1481180322
         * giftname : 光棍节脱单礼包
         * gname : 剑与家园
         * gtype : 6
         * iconurl : /allimgs/img_iapp/201612/_1481180262099.png
         * id : 1509699151
         * indexpy : 1
         * integral : 0
         * isintegral : 0
         * marks : 0
         * number : 7
         * onclicks : 0
         * operators : 未知
         * overtime : 2017-11-30
         * price : 10
         * ptype : 1,2,
         * sendtime : 1970-01-01
         * showimg :
         * surplus : 0
         * turl :
         * type : 1
         * typename : 礼包卡
         * uid : ff80808147fd49bc0147fd5240d10006
         * vtype : 2
         * vtypeimg : <i class='android'></i><i class='ios'></i>
         * vtypename : 手机客户端
         * wxcode : SJ_A7468
         */

        private String activityid;
        private String addtime;
        private String agtype;
        private String descs;
        private int exchanges;
        private String explains;
        private int flag;
        private String gcode;
        private String gid;
        private String giftname;
        private String gname;
        private int gtype;
        private String iconurl;
        private String id;
        private String indexpy;
        private int integral;
        private int isintegral;
        private int marks;
        private int number;
        private int onclicks;
        private String operators;
        private String overtime;
        private int price;
        private String ptype;
        private String sendtime;
        private String showimg;
        private int surplus;
        private String turl;
        private int type;
        private String typename;
        private String uid;
        private String vtype;
        private String vtypeimg;
        private String vtypename;
        private String wxcode;

        public void setActivityid(String activityid) {
            this.activityid = activityid;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public void setAgtype(String agtype) {
            this.agtype = agtype;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public void setExchanges(int exchanges) {
            this.exchanges = exchanges;
        }

        public void setExplains(String explains) {
            this.explains = explains;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public void setGcode(String gcode) {
            this.gcode = gcode;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public void setGtype(int gtype) {
            this.gtype = gtype;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIndexpy(String indexpy) {
            this.indexpy = indexpy;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public void setIsintegral(int isintegral) {
            this.isintegral = isintegral;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void setOnclicks(int onclicks) {
            this.onclicks = onclicks;
        }

        public void setOperators(String operators) {
            this.operators = operators;
        }

        public void setOvertime(String overtime) {
            this.overtime = overtime;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public void setShowimg(String showimg) {
            this.showimg = showimg;
        }

        public void setSurplus(int surplus) {
            this.surplus = surplus;
        }

        public void setTurl(String turl) {
            this.turl = turl;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setVtype(String vtype) {
            this.vtype = vtype;
        }

        public void setVtypeimg(String vtypeimg) {
            this.vtypeimg = vtypeimg;
        }

        public void setVtypename(String vtypename) {
            this.vtypename = vtypename;
        }

        public void setWxcode(String wxcode) {
            this.wxcode = wxcode;
        }

        public String getActivityid() {
            return activityid;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getAgtype() {
            return agtype;
        }

        public String getDescs() {
            return descs;
        }

        public int getExchanges() {
            return exchanges;
        }

        public String getExplains() {
            return explains;
        }

        public int getFlag() {
            return flag;
        }

        public String getGcode() {
            return gcode;
        }

        public String getGid() {
            return gid;
        }

        public String getGiftname() {
            return giftname;
        }

        public String getGname() {
            return gname;
        }

        public int getGtype() {
            return gtype;
        }

        public String getIconurl() {
            return iconurl;
        }

        public String getId() {
            return id;
        }

        public String getIndexpy() {
            return indexpy;
        }

        public int getIntegral() {
            return integral;
        }

        public int getIsintegral() {
            return isintegral;
        }

        public int getMarks() {
            return marks;
        }

        public int getNumber() {
            return number;
        }

        public int getOnclicks() {
            return onclicks;
        }

        public String getOperators() {
            return operators;
        }

        public String getOvertime() {
            return overtime;
        }

        public int getPrice() {
            return price;
        }

        public String getPtype() {
            return ptype;
        }

        public String getSendtime() {
            return sendtime;
        }

        public String getShowimg() {
            return showimg;
        }

        public int getSurplus() {
            return surplus;
        }

        public String getTurl() {
            return turl;
        }

        public int getType() {
            return type;
        }

        public String getTypename() {
            return typename;
        }

        public String getUid() {
            return uid;
        }

        public String getVtype() {
            return vtype;
        }

        public String getVtypeimg() {
            return vtypeimg;
        }

        public String getVtypename() {
            return vtypename;
        }

        public String getWxcode() {
            return wxcode;
        }
    }
}
