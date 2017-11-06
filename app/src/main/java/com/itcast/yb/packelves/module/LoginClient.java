package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yb on 2017/11/6.
 */

public interface LoginClient {
    //http://www.1688wan.com//majax.action?method=mobileUserLogin
    @POST("method=mobileUserLogin")
    @FormUrlEncoded
    Call<LoginBean> login(@Query("name") String name, @Query("pwd") String psw);
}
