package com.itcast.yb.packelves.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/11/2.
 */
public class KaiFuDetailsBean implements Serializable{


    /**
     * app : {"id":1484205897,"name":"不服小天王","developers":"上海游奇网络有限公司","appsize":"","version":"","logo":"/allimgs/img_iapp/201703/_1489124054272.jpg","download_addr":"","upload_time":1484205840,"add_time":1484205891,"state":1,"keywords":"不服小天王","operator":"小艾","typeid":2,"orderid":1,"description":"《不服小天王》是一款挂机冒险游戏，精美的游戏画面、炫酷流畅的人物技能、彻底解放双手，专为懒人玩家打造，让你一天24小时不间断的升级，一起来为了女神而战斗吧！天梯排位至关重要，无数的勇士在这里诞生，释放着心中对胜利的渴望，仰天怒吼吧！\u2014\u2014我不服！","good_evaluation":0,"bad_evaluation":0,"downloads":204,"views":0,"flag":1,"is_free":2,"freename":"下载免费/道具收费","video_addr":"http://m.qunhei.com/wxapi/wxlogin/unid/1688wan/gid/3331.html","statename":"已上架","flagname":"推荐","typename":"角色扮演","imagenum":0,"py":"BFXTW","vtype":"6","vtypename":"[WP]","vtypeimgs":"","downs":0,"yy":0,"yyname":"中文","isnetwork":0,"isgame":0,"true_good_evaluation":0,"true_bad_evaluation":0,"true_downloads":204,"true_views":0,"tz":"休闲,","fmoeny":0,"isintegral":0,"gflag":0,"libaoimg":"","zqshowimg":"","iszq":0,"zqurl":"http://","moulds":"0","bgimg":"","remarks":"《不服小天王》是一款精美细腻带有动漫色彩的角色扮演手游，专为懒人玩家打造","appscore":9,"appscore1":"9","appscore2":".0","trueappscore":9,"zqcode":"bfxtw","isnewgame":1,"packagename":"","zqflag":0,"zqscore":"1000"}
     * img : [{"id":273960,"address":"/allimgs/img_iapp/201701/_1484205875936.jpg","orderno":3,"state":0,"appid":"1484205897"},{"id":273961,"address":"/allimgs/img_iapp/201701/_1484205874901.jpg","orderno":2,"state":0,"appid":"1484205897"},{"id":273962,"address":"/allimgs/img_iapp/201701/_1484205873903.jpg","orderno":1,"state":0,"appid":"1484205897"},{"id":273963,"address":"/allimgs/img_iapp/201701/_1484205872964.jpg","orderno":0,"state":0,"appid":"1484205897"}]
     */

    private AppEntity app;
    private List<ImgEntity> img;

    public void setApp(AppEntity app) {
        this.app = app;
    }

    public void setImg(List<ImgEntity> img) {
        this.img = img;
    }

    public AppEntity getApp() {
        return app;
    }

    public List<ImgEntity> getImg() {
        return img;
    }

    public static class AppEntity {
        /**
         * id : 1484205897
         * name : 不服小天王
         * developers : 上海游奇网络有限公司
         * appsize :
         * version :
         * logo : /allimgs/img_iapp/201703/_1489124054272.jpg
         * download_addr :
         * upload_time : 1484205840
         * add_time : 1484205891
         * state : 1
         * keywords : 不服小天王
         * operator : 小艾
         * typeid : 2
         * orderid : 1
         * description : 《不服小天王》是一款挂机冒险游戏，精美的游戏画面、炫酷流畅的人物技能、彻底解放双手，专为懒人玩家打造，让你一天24小时不间断的升级，一起来为了女神而战斗吧！天梯排位至关重要，无数的勇士在这里诞生，释放着心中对胜利的渴望，仰天怒吼吧！——我不服！
         * good_evaluation : 0
         * bad_evaluation : 0
         * downloads : 204
         * views : 0
         * flag : 1
         * is_free : 2
         * freename : 下载免费/道具收费
         * video_addr : http://m.qunhei.com/wxapi/wxlogin/unid/1688wan/gid/3331.html
         * statename : 已上架
         * flagname : 推荐
         * typename : 角色扮演
         * imagenum : 0
         * py : BFXTW
         * vtype : 6
         * vtypename : [WP]
         * vtypeimgs :
         * downs : 0
         * yy : 0
         * yyname : 中文
         * isnetwork : 0
         * isgame : 0
         * true_good_evaluation : 0
         * true_bad_evaluation : 0
         * true_downloads : 204
         * true_views : 0
         * tz : 休闲,
         * fmoeny : 0
         * isintegral : 0
         * gflag : 0
         * libaoimg :
         * zqshowimg :
         * iszq : 0
         * zqurl : http://
         * moulds : 0
         * bgimg :
         * remarks : 《不服小天王》是一款精美细腻带有动漫色彩的角色扮演手游，专为懒人玩家打造
         * appscore : 9.0
         * appscore1 : 9
         * appscore2 : .0
         * trueappscore : 9
         * zqcode : bfxtw
         * isnewgame : 1
         * packagename :
         * zqflag : 0
         * zqscore : 1000
         */

        private int id;
        private String name;
        private String developers;
        private String appsize;
        private String version;
        private String logo;
        private String download_addr;
        private int upload_time;
        private int add_time;
        private int state;
        private String keywords;
        private String operator;
        private int typeid;
        private int orderid;
        private String description;
        private int good_evaluation;
        private int bad_evaluation;
        private int downloads;
        private int views;
        private int flag;
        private int is_free;
        private String freename;
        private String video_addr;
        private String statename;
        private String flagname;
        private String typename;
        private int imagenum;
        private String py;
        private String vtype;
        private String vtypename;
        private String vtypeimgs;
        private int downs;
        private int yy;
        private String yyname;
        private int isnetwork;
        private int isgame;
        private int true_good_evaluation;
        private int true_bad_evaluation;
        private int true_downloads;
        private int true_views;
        private String tz;
        private int fmoeny;
        private int isintegral;
        private int gflag;
        private String libaoimg;
        private String zqshowimg;
        private int iszq;
        private String zqurl;
        private String moulds;
        private String bgimg;
        private String remarks;
        private double appscore;
        private String appscore1;
        private String appscore2;
        private int trueappscore;
        private String zqcode;
        private int isnewgame;
        private String packagename;
        private int zqflag;
        private String zqscore;
        private String size;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDevelopers(String developers) {
            this.developers = developers;
        }

        public void setAppsize(String appsize) {
            this.appsize = appsize;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public void setDownload_addr(String download_addr) {
            this.download_addr = download_addr;
        }

        public void setUpload_time(int upload_time) {
            this.upload_time = upload_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setGood_evaluation(int good_evaluation) {
            this.good_evaluation = good_evaluation;
        }

        public void setBad_evaluation(int bad_evaluation) {
            this.bad_evaluation = bad_evaluation;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public void setFreename(String freename) {
            this.freename = freename;
        }

        public void setVideo_addr(String video_addr) {
            this.video_addr = video_addr;
        }

        public void setStatename(String statename) {
            this.statename = statename;
        }

        public void setFlagname(String flagname) {
            this.flagname = flagname;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public void setImagenum(int imagenum) {
            this.imagenum = imagenum;
        }

        public void setPy(String py) {
            this.py = py;
        }

        public void setVtype(String vtype) {
            this.vtype = vtype;
        }

        public void setVtypename(String vtypename) {
            this.vtypename = vtypename;
        }

        public void setVtypeimgs(String vtypeimgs) {
            this.vtypeimgs = vtypeimgs;
        }

        public void setDowns(int downs) {
            this.downs = downs;
        }

        public void setYy(int yy) {
            this.yy = yy;
        }

        public void setYyname(String yyname) {
            this.yyname = yyname;
        }

        public void setIsnetwork(int isnetwork) {
            this.isnetwork = isnetwork;
        }

        public void setIsgame(int isgame) {
            this.isgame = isgame;
        }

        public void setTrue_good_evaluation(int true_good_evaluation) {
            this.true_good_evaluation = true_good_evaluation;
        }

        public void setTrue_bad_evaluation(int true_bad_evaluation) {
            this.true_bad_evaluation = true_bad_evaluation;
        }

        public void setTrue_downloads(int true_downloads) {
            this.true_downloads = true_downloads;
        }

        public void setTrue_views(int true_views) {
            this.true_views = true_views;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public void setFmoeny(int fmoeny) {
            this.fmoeny = fmoeny;
        }

        public void setIsintegral(int isintegral) {
            this.isintegral = isintegral;
        }

        public void setGflag(int gflag) {
            this.gflag = gflag;
        }

        public void setLibaoimg(String libaoimg) {
            this.libaoimg = libaoimg;
        }

        public void setZqshowimg(String zqshowimg) {
            this.zqshowimg = zqshowimg;
        }

        public void setIszq(int iszq) {
            this.iszq = iszq;
        }

        public void setZqurl(String zqurl) {
            this.zqurl = zqurl;
        }

        public void setMoulds(String moulds) {
            this.moulds = moulds;
        }

        public void setBgimg(String bgimg) {
            this.bgimg = bgimg;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public void setAppscore(double appscore) {
            this.appscore = appscore;
        }

        public void setAppscore1(String appscore1) {
            this.appscore1 = appscore1;
        }

        public void setAppscore2(String appscore2) {
            this.appscore2 = appscore2;
        }

        public void setTrueappscore(int trueappscore) {
            this.trueappscore = trueappscore;
        }

        public void setZqcode(String zqcode) {
            this.zqcode = zqcode;
        }

        public void setIsnewgame(int isnewgame) {
            this.isnewgame = isnewgame;
        }

        public void setPackagename(String packagename) {
            this.packagename = packagename;
        }

        public void setZqflag(int zqflag) {
            this.zqflag = zqflag;
        }

        public void setZqscore(String zqscore) {
            this.zqscore = zqscore;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDevelopers() {
            return developers;
        }

        public String getAppsize() {
            return appsize;
        }

        public String getVersion() {
            return version;
        }

        public String getLogo() {
            return logo;
        }

        public String getDownload_addr() {
            return download_addr;
        }

        public int getUpload_time() {
            return upload_time;
        }

        public int getAdd_time() {
            return add_time;
        }

        public int getState() {
            return state;
        }

        public String getKeywords() {
            return keywords;
        }

        public String getOperator() {
            return operator;
        }

        public int getTypeid() {
            return typeid;
        }

        public int getOrderid() {
            return orderid;
        }

        public String getDescription() {
            return description;
        }

        public int getGood_evaluation() {
            return good_evaluation;
        }

        public int getBad_evaluation() {
            return bad_evaluation;
        }

        public int getDownloads() {
            return downloads;
        }

        public int getViews() {
            return views;
        }

        public int getFlag() {
            return flag;
        }

        public int getIs_free() {
            return is_free;
        }

        public String getFreename() {
            return freename;
        }

        public String getVideo_addr() {
            return video_addr;
        }

        public String getStatename() {
            return statename;
        }

        public String getFlagname() {
            return flagname;
        }

        public String getTypename() {
            return typename;
        }

        public int getImagenum() {
            return imagenum;
        }

        public String getPy() {
            return py;
        }

        public String getVtype() {
            return vtype;
        }

        public String getVtypename() {
            return vtypename;
        }

        public String getVtypeimgs() {
            return vtypeimgs;
        }

        public int getDowns() {
            return downs;
        }

        public int getYy() {
            return yy;
        }

        public String getYyname() {
            return yyname;
        }

        public int getIsnetwork() {
            return isnetwork;
        }

        public int getIsgame() {
            return isgame;
        }

        public int getTrue_good_evaluation() {
            return true_good_evaluation;
        }

        public int getTrue_bad_evaluation() {
            return true_bad_evaluation;
        }

        public int getTrue_downloads() {
            return true_downloads;
        }

        public int getTrue_views() {
            return true_views;
        }

        public String getTz() {
            return tz;
        }

        public int getFmoeny() {
            return fmoeny;
        }

        public int getIsintegral() {
            return isintegral;
        }

        public int getGflag() {
            return gflag;
        }

        public String getLibaoimg() {
            return libaoimg;
        }

        public String getZqshowimg() {
            return zqshowimg;
        }

        public int getIszq() {
            return iszq;
        }

        public String getZqurl() {
            return zqurl;
        }

        public String getMoulds() {
            return moulds;
        }

        public String getBgimg() {
            return bgimg;
        }

        public String getRemarks() {
            return remarks;
        }

        public double getAppscore() {
            return appscore;
        }

        public String getAppscore1() {
            return appscore1;
        }

        public String getAppscore2() {
            return appscore2;
        }

        public int getTrueappscore() {
            return trueappscore;
        }

        public String getZqcode() {
            return zqcode;
        }

        public int getIsnewgame() {
            return isnewgame;
        }

        public String getPackagename() {
            return packagename;
        }

        public int getZqflag() {
            return zqflag;
        }

        public String getZqscore() {
            return zqscore;
        }
    }

    public static class ImgEntity {
        /**
         * id : 273960
         * address : /allimgs/img_iapp/201701/_1484205875936.jpg
         * orderno : 3
         * state : 0
         * appid : 1484205897
         */

        private int id;
        private String address;
        private int orderno;
        private int state;
        private String appid;

        public void setId(int id) {
            this.id = id;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setOrderno(int orderno) {
            this.orderno = orderno;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public int getId() {
            return id;
        }

        public String getAddress() {
            return address;
        }

        public int getOrderno() {
            return orderno;
        }

        public int getState() {
            return state;
        }

        public String getAppid() {
            return appid;
        }
    }
}
