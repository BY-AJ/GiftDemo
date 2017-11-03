package com.itcast.yb.packelves.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/11/1.
 */
public class HotBean {


    /**
     * flag : true
     * info : {"push1":[{"id":69,"appid":"1499855012","type":1,"clicks":11653,"flag":1,"platform":0,"name":"扑客捕鱼","typename":"休闲益智","logo":"/allimgs/img_iapp/201707/_1499854956984.png","addtime":"2017-07-12 18:21:14.0"},{"id":61,"appid":"1491811614","type":1,"clicks":6523,"flag":1,"platform":0,"name":"天堂之心","typename":"角色扮演","logo":"/allimgs/img_iapp/201704/_1491811561176.png","size":"402M","addtime":"2017-05-16 13:38:10.0"},{"id":53,"appid":"1493025355","type":1,"clicks":1852,"flag":1,"platform":0,"name":"消灭都市","typename":"冒险解密","logo":"/allimgs/img_iapp/201704/_1493025189491.jpg","size":"82MB","addtime":"2017-05-16 13:24:30.0"}],"push2":[{"id":74,"appid":"1501579284","type":0,"clicks":10012,"flag":1,"platform":0,"name":"狼人杀加强版","typename":"棋牌桌游","logo":"/allimgs/img_iapp/201709/_1505532475140.png","addtime":"2017-09-16 11:36:58.0"},{"id":70,"appid":"1503555438","type":0,"clicks":10000,"flag":1,"platform":0,"name":"掌读看书","typename":"资讯阅读","logo":"/allimgs/img_iapp/201708/_1503555393312.jpg","addtime":"2017-08-24 14:15:05.0"},{"id":9,"appid":"1443491252","type":0,"clicks":4533,"flag":1,"platform":0,"name":"王者荣耀","typename":"动作格斗","logo":"/allimgs/img_iapp/201509/_1443491274999.png","size":"360M","addtime":"2017-05-27 14:57:25.0"},{"id":67,"appid":"2491764","type":0,"clicks":1000,"flag":1,"platform":0,"name":"秦时明月","typename":"角色扮演","logo":"/userfiles/applogo/_1409295596291.jpg","size":"93.2 MB","addtime":"2017-05-17 10:42:12.0"},{"id":59,"appid":"1415092413","type":0,"clicks":5896,"flag":1,"platform":0,"name":"苍穹之剑","typename":"角色扮演","logo":"/allimgs/img_iapp/201704/_1491983013939.png","addtime":"2017-05-16 13:36:27.0"},{"id":57,"appid":"1492410664","type":0,"clicks":2689,"flag":1,"platform":0,"name":"荆棘王座","typename":"角色扮演","logo":"/allimgs/img_iapp/201708/_1503389960085.png","addtime":"2017-05-16 13:35:35.0"},{"id":55,"appid":"1494404849","type":0,"clicks":4689,"flag":1,"platform":0,"name":"剑雨苍穹","typename":"角色扮演","logo":"/allimgs/img_iapp/201705/_1494404827344.jpg","addtime":"2017-05-16 13:31:31.0"},{"id":11,"appid":"1421465673","type":0,"clicks":4522,"flag":1,"platform":0,"name":"梦幻西游手游","typename":"角色扮演","logo":"/allimgs/img_iapp/201504/_1429091380602.jpg","size":"280M","addtime":"2017-05-15 09:28:57.0"},{"id":28,"appid":"1467978839","type":0,"clicks":2468,"flag":1,"platform":0,"name":"阴阳师","typename":"角色扮演","logo":"/allimgs/img_iapp/201610/_1476346965874.jpeg","addtime":"2017-05-12 13:53:28.0"}]}
     */

    private boolean flag;
    private InfoEntity info;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public boolean getFlag() {
        return flag;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public static class InfoEntity {
        /**
         * push1 : [{"id":69,"appid":"1499855012","type":1,"clicks":11653,"flag":1,"platform":0,"name":"扑客捕鱼","typename":"休闲益智","logo":"/allimgs/img_iapp/201707/_1499854956984.png","addtime":"2017-07-12 18:21:14.0"},{"id":61,"appid":"1491811614","type":1,"clicks":6523,"flag":1,"platform":0,"name":"天堂之心","typename":"角色扮演","logo":"/allimgs/img_iapp/201704/_1491811561176.png","size":"402M","addtime":"2017-05-16 13:38:10.0"},{"id":53,"appid":"1493025355","type":1,"clicks":1852,"flag":1,"platform":0,"name":"消灭都市","typename":"冒险解密","logo":"/allimgs/img_iapp/201704/_1493025189491.jpg","size":"82MB","addtime":"2017-05-16 13:24:30.0"}]
         * push2 : [{"id":74,"appid":"1501579284","type":0,"clicks":10012,"flag":1,"platform":0,"name":"狼人杀加强版","typename":"棋牌桌游","logo":"/allimgs/img_iapp/201709/_1505532475140.png","addtime":"2017-09-16 11:36:58.0"},{"id":70,"appid":"1503555438","type":0,"clicks":10000,"flag":1,"platform":0,"name":"掌读看书","typename":"资讯阅读","logo":"/allimgs/img_iapp/201708/_1503555393312.jpg","addtime":"2017-08-24 14:15:05.0"},{"id":9,"appid":"1443491252","type":0,"clicks":4533,"flag":1,"platform":0,"name":"王者荣耀","typename":"动作格斗","logo":"/allimgs/img_iapp/201509/_1443491274999.png","size":"360M","addtime":"2017-05-27 14:57:25.0"},{"id":67,"appid":"2491764","type":0,"clicks":1000,"flag":1,"platform":0,"name":"秦时明月","typename":"角色扮演","logo":"/userfiles/applogo/_1409295596291.jpg","size":"93.2 MB","addtime":"2017-05-17 10:42:12.0"},{"id":59,"appid":"1415092413","type":0,"clicks":5896,"flag":1,"platform":0,"name":"苍穹之剑","typename":"角色扮演","logo":"/allimgs/img_iapp/201704/_1491983013939.png","addtime":"2017-05-16 13:36:27.0"},{"id":57,"appid":"1492410664","type":0,"clicks":2689,"flag":1,"platform":0,"name":"荆棘王座","typename":"角色扮演","logo":"/allimgs/img_iapp/201708/_1503389960085.png","addtime":"2017-05-16 13:35:35.0"},{"id":55,"appid":"1494404849","type":0,"clicks":4689,"flag":1,"platform":0,"name":"剑雨苍穹","typename":"角色扮演","logo":"/allimgs/img_iapp/201705/_1494404827344.jpg","addtime":"2017-05-16 13:31:31.0"},{"id":11,"appid":"1421465673","type":0,"clicks":4522,"flag":1,"platform":0,"name":"梦幻西游手游","typename":"角色扮演","logo":"/allimgs/img_iapp/201504/_1429091380602.jpg","size":"280M","addtime":"2017-05-15 09:28:57.0"},{"id":28,"appid":"1467978839","type":0,"clicks":2468,"flag":1,"platform":0,"name":"阴阳师","typename":"角色扮演","logo":"/allimgs/img_iapp/201610/_1476346965874.jpeg","addtime":"2017-05-12 13:53:28.0"}]
         */

        private List<Push1Entity> push1;
        private List<Push2Entity> push2;

        public void setPush1(List<Push1Entity> push1) {
            this.push1 = push1;
        }

        public void setPush2(List<Push2Entity> push2) {
            this.push2 = push2;
        }

        public List<Push1Entity> getPush1() {
            return push1;
        }

        public List<Push2Entity> getPush2() {
            return push2;
        }

        public static class Push1Entity implements Serializable {
            /**
             * id : 69
             * appid : 1499855012
             * type : 1
             * clicks : 11653
             * flag : 1
             * platform : 0
             * name : 扑客捕鱼
             * typename : 休闲益智
             * logo : /allimgs/img_iapp/201707/_1499854956984.png
             * addtime : 2017-07-12 18:21:14.0
             */

            private int id;
            private int appid;
            private int type;
            private int clicks;
            private int flag;
            private int platform;
            private String name;
            private String typename;
            private String logo;
            private String addtime;
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

            public void setAppid(int appid) {
                this.appid = appid;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setClicks(int clicks) {
                this.clicks = clicks;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public int getId() {
                return id;
            }

            public int getAppid() {
                return appid;
            }

            public int getType() {
                return type;
            }

            public int getClicks() {
                return clicks;
            }

            public int getFlag() {
                return flag;
            }

            public int getPlatform() {
                return platform;
            }

            public String getName() {
                return name;
            }

            public String getTypename() {
                return typename;
            }

            public String getLogo() {
                return logo;
            }

            public String getAddtime() {
                return addtime;
            }
        }

        public static class Push2Entity implements Serializable{
            /**
             * id : 74
             * appid : 1501579284
             * type : 0
             * clicks : 10012
             * flag : 1
             * platform : 0
             * name : 狼人杀加强版
             * typename : 棋牌桌游
             * logo : /allimgs/img_iapp/201709/_1505532475140.png
             * addtime : 2017-09-16 11:36:58.0
             */

            private int id;
            private int appid;
            private int type;
            private int clicks;
            private int flag;
            private int platform;
            private String name;
            private String typename;
            private String logo;
            private String addtime;
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

            public void setAppid(int appid) {
                this.appid = appid;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setClicks(int clicks) {
                this.clicks = clicks;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public int getId() {
                return id;
            }

            public int getAppid() {
                return appid;
            }

            public int getType() {
                return type;
            }

            public int getClicks() {
                return clicks;
            }

            public int getFlag() {
                return flag;
            }

            public int getPlatform() {
                return platform;
            }

            public String getName() {
                return name;
            }

            public String getTypename() {
                return typename;
            }

            public String getLogo() {
                return logo;
            }

            public String getAddtime() {
                return addtime;
            }
        }
    }
}
