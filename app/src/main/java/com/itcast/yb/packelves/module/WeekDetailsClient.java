package com.itcast.yb.packelves.module;

import com.itcast.yb.packelves.bean.WeekDetailsBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yb on 2017/11/1.
 */

public interface WeekDetailsClient {
    @POST("majax.action?method=bdxqschild")
    Call<WeekDetailsBean> getWeekDetailsDatasForServer(@Query("id") int id);

}
