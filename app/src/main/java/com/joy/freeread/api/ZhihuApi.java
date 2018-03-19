package com.joy.freeread.api;

import com.joy.freeread.bean.zhihu.News;
import com.joy.freeread.bean.zhihu.NewsTimeLine;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public interface ZhihuApi {
    @GET("news/latest")
    Call<NewsTimeLine> getLatestNews();

    @GET("news/before/{time}")
    Call<NewsTimeLine> getBeforeNews(@Path("time") String time);

    @GET("news/{id}")
    Call<News> getDetailNews(@Path("id") String id);
}
