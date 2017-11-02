package com.itcast.yb.packelves.network;

import com.itcast.yb.packelves.bean.DownloadInfoBean;
import com.itcast.yb.packelves.bean.FeatureInfoBean;
import com.itcast.yb.packelves.bean.GiftInfoBean;
import com.itcast.yb.packelves.bean.OpenServiceInfoBean;
import com.itcast.yb.packelves.bean.WeekDetailsBean;
import com.itcast.yb.packelves.module.DownloadClient;
import com.itcast.yb.packelves.module.FeatureClient;
import com.itcast.yb.packelves.module.GiftClient;
import com.itcast.yb.packelves.module.OpenServiceClient;
import com.itcast.yb.packelves.module.WeekDetailsClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求类
 * Created by yb on 2017/10/29.
 */

public class RequestNetwork {
    public static final String SERVER_URL="http://www.1688wan.com/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

    //获取礼物数据
    public static Call<GiftInfoBean> getGiftClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient.build())
                .build();
        GiftClient client = retrofit.create(GiftClient.class);
        return client.getGiftDataForServer();
    }

    //获取特色数据
    public static Call<FeatureInfoBean> getFeatureClient(String method) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient.build())
                .build();
        FeatureClient client = retrofit.create(FeatureClient.class);
        return client.getFeatureDataForServer(method);
    }

    //获取开服数据
    public static Call<OpenServiceInfoBean> getOpenServiceClient(String method) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient.build())
                .build();
        OpenServiceClient client = retrofit.create(OpenServiceClient.class);
        return client.getOpenServiceDataForService(method);
    }

    //获取暴打星期三详情页数据
    public static Call<WeekDetailsBean> getWeekDetailsClient(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient.build())
                .build();
        WeekDetailsClient client = retrofit.create(WeekDetailsClient.class);
        return client.getWeekDetailsDatasForServer(id);
    }

    //获取特色下载页的数据
    public static Call<DownloadInfoBean> getDownloadClient(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient.build())
                .build();
        DownloadClient client = retrofit.create(DownloadClient.class);
        return client.getDownloadDataForServer(id);
    }
}
