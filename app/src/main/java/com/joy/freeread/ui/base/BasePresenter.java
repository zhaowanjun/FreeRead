package com.joy.freeread.ui.base;

import com.joy.freeread.api.GankApi;
import com.joy.freeread.api.VideoApi;
import com.joy.freeread.api.ZhihuApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public abstract class BasePresenter {
    private final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";
    private final String GANK_BASE_URL = "http://gank.io/api/";
    private final String VIDEO_BASE_URL = "http://baobab.kaiyanapp.com/api/";
    protected ZhihuApi mZhihuApi;
    protected GankApi mGankApi;
    protected VideoApi mVideoApi;

    public BasePresenter() {
        Retrofit zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(ZHIHU_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit gankRetrofit = new Retrofit.Builder()
                .baseUrl(GANK_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit videoRetrofit = new Retrofit.Builder()
                .baseUrl(VIDEO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mZhihuApi = zhihuRetrofit.create(ZhihuApi.class);
        mGankApi = gankRetrofit.create(GankApi.class);
        mVideoApi = videoRetrofit.create(VideoApi.class);

    }

    protected DataLoadStateListener dataLoadStateListener;

    public void setDataLoadStateListener(DataLoadStateListener dataLoadStateListener) {
        this.dataLoadStateListener = dataLoadStateListener;
    }

    public interface DataLoadStateListener {
        void dataIsLoaded();
    }

}
