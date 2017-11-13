package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.ReceiveBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yb on 2017/11/13.
 */

public interface ReceiveClient {
    @POST("majax.action?method=GiftCheck")
    @FormUrlEncoded
    Call<ReceiveBean> receiveVerifyForServer(@Field("uid") String uid, @Field("gid") String gid);
    //Call<ReceiveBean> receiveVerifyForServer(@QueryMap Map<String,String> map);
}
