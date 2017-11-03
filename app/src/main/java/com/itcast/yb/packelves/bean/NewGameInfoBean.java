package com.itcast.yb.packelves.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lenovo on 2017/10/30.
 */
public class NewGameInfoBean {
    private int pageno;
    private List<ListEntity> list;
    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getPageno() {
        return pageno;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity implements Parcelable{
        /**
         * id : 20171027
         * name : 新游周刊第146期
         * author : 小萨
         * authorimg : /allimgs/img_iad/_1490083628056.jpg
         * iconurl : /allimgs/img_iapp/201710/_1509108442726.jpg
         * showiconurl : /allimgs/img_iapp/201710/_1509109537056.jpg
         * oriiconurl : /allimgs/img_iapp/201710/_1509108721295.jpg
         * descs : 马上就要到万圣节了，不知小伙伴们会打扮成什么样去要糖果呢？如果还没决定的话就来看看这一期的新游周刊，保证这些新游会启发你的大脑！
         * newsurl : http://www.1688wan.com/news/yc/201706/info_185717.html
         * newsicon : http://i1.1688wan.com/allimgs/img_inews/201706/_1497339841158.jpg
         * flag : 1
         * views : 74
         * orderno : 146
         * addtime : 2017-10-27
         * keyword : 万圣节，新游周刊，灼眼的夏娜手游，新游推荐
         * newid : 0
         * nums : 9
         */

        private int id;
        private String name;
        private String author;
        private String authorimg;
        private String iconurl;
        private String showiconurl;
        private String oriiconurl;
        private String descs;
        private String newsurl;
        private String newsicon;
        private int flag;
        private int views;
        private String orderno;
        private String addtime;
        private String keyword;
        private int newid;
        private int nums;

        protected ListEntity(Parcel in) {
            id = in.readInt();
            name = in.readString();
            author = in.readString();
            authorimg = in.readString();
            iconurl = in.readString();
            showiconurl = in.readString();
            oriiconurl = in.readString();
            descs = in.readString();
            newsurl = in.readString();
            newsicon = in.readString();
            flag = in.readInt();
            views = in.readInt();
            orderno = in.readString();
            addtime = in.readString();
            keyword = in.readString();
            newid = in.readInt();
            nums = in.readInt();
        }

        public static final Creator<ListEntity> CREATOR = new Creator<ListEntity>() {
            @Override
            public ListEntity createFromParcel(Parcel in) {
                return new ListEntity(in);
            }

            @Override
            public ListEntity[] newArray(int size) {
                return new ListEntity[size];
            }
        };

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setAuthorimg(String authorimg) {
            this.authorimg = authorimg;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public void setShowiconurl(String showiconurl) {
            this.showiconurl = showiconurl;
        }

        public void setOriiconurl(String oriiconurl) {
            this.oriiconurl = oriiconurl;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public void setNewsurl(String newsurl) {
            this.newsurl = newsurl;
        }

        public void setNewsicon(String newsicon) {
            this.newsicon = newsicon;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public void setNewid(int newid) {
            this.newid = newid;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String getAuthorimg() {
            return authorimg;
        }

        public String getIconurl() {
            return iconurl;
        }

        public String getShowiconurl() {
            return showiconurl;
        }

        public String getOriiconurl() {
            return oriiconurl;
        }

        public String getDescs() {
            return descs;
        }

        public String getNewsurl() {
            return newsurl;
        }

        public String getNewsicon() {
            return newsicon;
        }

        public int getFlag() {
            return flag;
        }

        public int getViews() {
            return views;
        }

        public String getOrderno() {
            return orderno;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getKeyword() {
            return keyword;
        }

        public int getNewid() {
            return newid;
        }

        public int getNums() {
            return nums;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(author);
            dest.writeString(authorimg);
            dest.writeString(iconurl);
            dest.writeString(showiconurl);
            dest.writeString(oriiconurl);
            dest.writeString(descs);
            dest.writeString(newsurl);
            dest.writeString(newsicon);
            dest.writeInt(flag);
            dest.writeInt(views);
            dest.writeString(orderno);
            dest.writeString(addtime);
            dest.writeString(keyword);
            dest.writeInt(newid);
            dest.writeInt(nums);
        }
    }
}
