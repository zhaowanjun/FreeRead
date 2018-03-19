package com.joy.freeread.api;

import com.joy.freeread.bean.gank.Meizhi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public interface GankApi {
    @GET("data/福利/10/{page}")
    Call<Meizhi> getMeiZhiData(@Path("page") int page);
}
