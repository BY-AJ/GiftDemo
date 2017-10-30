package com.itcast.yb.packelves.network;

import com.itcast.yb.packelves.bean.FeatureInfoBean;
import com.itcast.yb.packelves.bean.GiftInfoBean;
import com.itcast.yb.packelves.module.FeatureClient;
import com.itcast.yb.packelves.module.GiftClient;

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

    public static Call<GiftInfoBean> getGiftClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient.build())
                .build();
        GiftClient client = retrofit.create(GiftClient.class);
        return client.getGiftDataForServer();
    }

    public static Call<FeatureInfoBean> getFeatureClient(String method) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient.build())
                .build();
        FeatureClient client = retrofit.create(FeatureClient.class);
        return client.getFeatureDataForServer(method);
    }
}
