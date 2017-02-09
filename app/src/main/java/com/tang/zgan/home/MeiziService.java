package com.tang.zgan.home;

import com.tang.zgan.bean.Meizi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tangyc on 2017/2/9.
 */

public interface MeiziService {
    @GET("福利/{date}/{num}")
    public Call<Meizi> getMeizi(@Path("date") String date, @Path("num") String num);
}
