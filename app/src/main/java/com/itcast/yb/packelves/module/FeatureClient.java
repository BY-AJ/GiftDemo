package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.FeatureInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yb on 2017/10/30.
 */

//private static final String BEAT_URL ="majax.action?method=bdxqs&pageNo=0";

public interface FeatureClient {
    @GET("majax.action")
    Call<FeatureInfoBean> getFeatureDataForServer(@Query("method") String method);
}
