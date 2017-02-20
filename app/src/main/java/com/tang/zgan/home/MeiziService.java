package com.tang.zgan.home;

import com.tang.zgan.home.model.vo.Meizi;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tangyc on 2017/2/9.
 */

public interface MeiziService {
    @GET("福利/{count}/{num}")
    public Observable<Meizi> getMeizi(@Path("count") String count, @Path("num") String num);
}
