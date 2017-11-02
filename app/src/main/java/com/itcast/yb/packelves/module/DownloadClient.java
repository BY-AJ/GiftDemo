package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.DownloadInfoBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yb on 2017/11/2.
 */

public interface DownloadClient {
    //http://www.1688wan.com/majax.action?method=getAppInfo&id=?
    @POST("majax.action?method=getAppInfo")
    Call<DownloadInfoBean> getDownloadDataForServer(@Query("id") int id);
}
