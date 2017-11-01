package com.itcast.yb.packelves.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yb on 2017/10/30.
 */

public class FeatureInfoBean {

    public ArrayList<EntityInfo> list;
    public int pageno;

    public static class EntityInfo implements Parcelable{
        public String addtime;
        public String name;
        public String iconurl;
        public String descs;
        public int sid;

        protected EntityInfo(Parcel in) {
            addtime = in.readString();
            name = in.readString();
            iconurl = in.readString();
            descs = in.readString();
            sid = in.readInt();
        }

        public static final Creator<EntityInfo> CREATOR = new Creator<EntityInfo>() {
            @Override
            public EntityInfo createFromParcel(Parcel in) {
                return new EntityInfo(in);
            }

            @Override
            public EntityInfo[] newArray(int size) {
                return new EntityInfo[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(addtime);
            dest.writeString(name);
            dest.writeString(iconurl);
            dest.writeString(descs);
            dest.writeInt(sid);
        }
    }
}
