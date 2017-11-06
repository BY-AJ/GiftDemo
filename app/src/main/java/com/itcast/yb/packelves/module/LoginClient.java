package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.LoginBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by yb on 2017/11/6.
 */

public interface LoginClient {
    //http://www.1688wan.com//majax.action?method=mobileUserLogin
    @POST("method=mobileUserLogin")
    @FormUrlEncoded
    Call<LoginBean> login(@QueryMap Map<String,String> map);
}
