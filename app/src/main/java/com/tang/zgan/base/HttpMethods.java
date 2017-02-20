package com.tang.zgan.base;

import com.tang.zgan.C;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tangyc on 2017/2/15.
 */

public class HttpMethods {
    private static HttpMethods http=null;
    private Retrofit retrofit=null;
    private HttpMethods(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(C.TIME_OUT, TimeUnit.SECONDS);
        retrofit=new Retrofit.Builder()
                .baseUrl(C.API)
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public synchronized static HttpMethods getHttpMethods(){
        if(http==null)
        {
            synchronized (HttpMethods.class)
            {
                if(http==null)
                {
                    http=new HttpMethods();
                }
            }
        }
        return http;
    }
    public   Retrofit getRetrofit(){
        return  retrofit;
    }
}
