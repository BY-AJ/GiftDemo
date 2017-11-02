package com.itcast.yb.packelves.utils;

import com.itcast.yb.packelves.bean.HotBean;
import com.itcast.yb.packelves.bean.KaiCeDetailsBean;
import com.itcast.yb.packelves.bean.KaiCeInfoBean;
import com.itcast.yb.packelves.bean.NewGameInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2017/10/30.
*/
public interface HttpService {
    @GET("/majax.action?method=getWeekll")
    Call<NewGameInfoBean> querGift(@Query("pageNo") int pageNo);



    @GET("/majax.action?method=hotpushForAndroid")
    Call<HotBean> queryHot();


    //开测
    @GET("/majax.action?method=getWebfutureTest")
    Call<KaiCeInfoBean> queryKaiCe();
    //开测详情
    @POST("/majax.action?method=getAppInfo")
    Call<KaiCeDetailsBean> queryKaiCeDetails(@Query("id") String gid);
}
