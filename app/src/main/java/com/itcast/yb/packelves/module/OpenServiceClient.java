package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.OpenServiceInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 获取开服数据接口类
 * Created by yb on 2017/10/31.
 */

public interface OpenServiceClient {
    @GET("majax.action")
    Call<OpenServiceInfoBean> getOpenServiceDataForService(@Query("method") String method);
}
