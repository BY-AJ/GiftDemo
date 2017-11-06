package com.itcast.yb.packelves.bean;

/**
 * Created by yb on 2017/11/6.
 */

public class LoginBean {
    public boolean flag;
    public String returnMsg;
    public ReturnInfo info;

    public class ReturnInfo{
        public String uid;
        public String nickname;
    }
}
