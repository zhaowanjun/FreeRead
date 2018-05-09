package com.joy.freeread.api;

import com.joy.freeread.bean.gank.DayData;
import com.joy.freeread.bean.gank.GankDay;
import com.joy.freeread.bean.gank.Meizhi;
import com.joy.freeread.bean.video.DataBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public interface GankApi {
    @GET("day/history")
    Call<GankDay> getGankDay();

    @GET("day/{date}")
    Call<DayData> getDayData(@Path("date") String date);

    @GET("data/福利/15/{page}")
    Call<Meizhi> getMeiZhi(@Path("page") int page);
}
