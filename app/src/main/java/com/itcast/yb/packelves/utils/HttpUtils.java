package com.itcast.yb.packelves.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/10/30.
 */
//  http://www.1688wan.com/majax.action?method=hotpushForAndroid
public class HttpUtils {
    public static final String BASE_URL="http://www.1688wan.com";
    private static HttpService mHttpService=null;

    public static HttpService creat(){
        if(mHttpService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mHttpService=retrofit.create(HttpService.class);
        }
        return mHttpService;
    }

}
