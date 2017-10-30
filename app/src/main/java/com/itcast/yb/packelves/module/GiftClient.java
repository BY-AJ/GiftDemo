package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.GiftInfoBean;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * 礼包类
 * Created by yb on 2017/10/29.
 */

public interface GiftClient {
    @POST("majax.action?method=getGiftList")
    Call<GiftInfoBean> getGiftDataForServer();
}
