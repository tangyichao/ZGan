package com.tang.zgan.home;

import com.tang.zgan.home.model.vo.AndroidArticle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tangyc on 2017/2/10.
 */

public interface AndroidService {
    @GET("Android/{date}/{num}")
    public Observable<AndroidArticle> getAndroid(@Path("date") String date, @Path("num") String num);
}
