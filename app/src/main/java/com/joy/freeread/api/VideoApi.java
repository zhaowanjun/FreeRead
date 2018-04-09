package com.joy.freeread.api;

import com.joy.freeread.bean.video.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public interface VideoApi {

    @GET("v2/feed?num=2")
    Call<Videos> getFirstPage();

    @GET("v2/feed")
    Call<Videos> getNextPage(@Query("date") String date, @Query("num") String num);
}
