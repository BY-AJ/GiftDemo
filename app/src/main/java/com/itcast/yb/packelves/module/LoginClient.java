package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yb on 2017/11/6.
 */

public interface LoginClient {
    //http://www.1688wan.com//majax.action?method=mobileUserLogin
    @POST("method=mobileUserLogin")
    @FormUrlEncoded
    Call<LoginBean> login(@Field("name") String name,@Field("psw") String psw);
}
