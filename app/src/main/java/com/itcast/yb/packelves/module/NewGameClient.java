package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.GameDetailsBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yb on 2017/11/3.
 */

public interface NewGameClient {
    //http://www.1688wan.com/majax.action?method=getWeekllChid
    @POST("majax.action?method=getWeekllChid")
    Call<GameDetailsBean> getGameDetailsDatasForServer(@Query("id") int id);
}
