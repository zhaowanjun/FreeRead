package com.joy.freeread.api;

import com.joy.freeread.bean.gank.DailyGankBean;
import com.joy.freeread.bean.gank.MeizhiBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public interface GankApi {

    @GET("day/{date}")
    Call<DailyGankBean> getDailyGank(@Path("date") String date);

    @GET("data/福利/15/{page}")
    Call<MeizhiBean> getMeiZhi(@Path("page") int page);
}
