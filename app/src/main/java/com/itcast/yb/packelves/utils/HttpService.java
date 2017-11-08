package com.itcast.yb.packelves.utils;

import com.itcast.yb.packelves.bean.DownloadInfoBean;
import com.itcast.yb.packelves.bean.GiftDetailsBean;
import com.itcast.yb.packelves.bean.HotBean;
import com.itcast.yb.packelves.bean.KaiCeDetailsBean;
import com.itcast.yb.packelves.bean.KaiCeInfoBean;
import com.itcast.yb.packelves.bean.KaiFuDetailsBean;
import com.itcast.yb.packelves.bean.LoginBean;
import com.itcast.yb.packelves.bean.NewGameInfoBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    //开服详情
    @POST("/majax.action?method=getAppInfo")
    Call<KaiFuDetailsBean> queryKaiFuDetails(@Query("id") String gid);
    //精品推荐详情
   @POST("/majax.action?method=getAppInfo")
    Call<DownloadInfoBean> queryJinPinDetails(@Query("id") int appid);
    //礼包详情
    @POST("/majax.action?method=getGiftInfo")
    Call<GiftDetailsBean> querGiftDetails(@Query("id") int id);

    //注册 http://www.1688wan.com//webmember.action?method=userRegisterForMobile
    @FormUrlEncoded
    @POST("/webmember.action?method=userRegisterForMobile")
    Call<LoginBean> queryRigester(@Field("uname")String name,@Field("pwd") String pwd,@Field("nickname")String nickname,@Field("mid")String mid);

}
