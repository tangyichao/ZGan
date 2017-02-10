package com.tang.zgan.home;

import com.tang.zgan.bean.AndroidArticle;
import com.tang.zgan.bean.Meizi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tangyc on 2017/2/10.
 */

public interface AndroidService {
    @GET("Android/{date}/{num}")
    public Call<AndroidArticle> getAndroid(@Path("date") String date, @Path("num") String num);
}
