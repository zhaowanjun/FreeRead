package com.joy.freeread.ui.presenter;

import android.content.Context;
import android.content.Intent;

import com.joy.freeread.bean.zhihu.News;
import com.joy.freeread.bean.zhihu.NewsTimeLine;
import com.joy.freeread.bean.zhihu.Story;
import com.joy.freeread.bean.zhihu.TopStories;
import com.joy.freeread.ui.activity.DetailActivity;
import com.joy.freeread.ui.adapter.ZhihuAdapter;
import com.joy.freeread.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public class ZhihuPresenter extends BasePresenter {

    private ZhihuAdapter mZhihuAdapter;
    private Context mContext;

    List<Story> stories = new ArrayList<>();
    private String time ;
    private List<TopStories> mTop_stories;

    public ZhihuPresenter(ZhihuAdapter zhihuAdapter, Context context) {
        mZhihuAdapter = zhihuAdapter;
        mContext = context;
    }

    public void getLatestNews() {
        Call<NewsTimeLine> latestNews = mZhihuApi.getLatestNews();
        latestNews.enqueue(new Callback<NewsTimeLine>() {
            @Override
            public void onResponse(Call<NewsTimeLine> call, Response<NewsTimeLine> response) {
                NewsTimeLine mLatestNews = response.body();
                time = mLatestNews.getDate();

                mTop_stories = mLatestNews.getTop_stories();
                mZhihuAdapter.setTopStories(mTop_stories);

                stories.clear();
                stories.addAll(mLatestNews.getStories());
                mZhihuAdapter.setData(stories);
                mZhihuAdapter.notifyDataSetChanged();

                //加载完成
                if(dataLoadStateListener != null) {
                    dataLoadStateListener.dataIsLoaded();
                }
            }

            @Override
            public void onFailure(Call<NewsTimeLine> call, Throwable t) {

            }
        });
    }

    public void getMoreNews() {
        Call<NewsTimeLine> beforeNews = mZhihuApi.getBeforeNews(time);
        beforeNews.enqueue(new Callback<NewsTimeLine>() {
            @Override
            public void onResponse(Call<NewsTimeLine> call, Response<NewsTimeLine> response) {
                NewsTimeLine mBeforeNews = response.body();
                time = mBeforeNews.getDate();

                stories.addAll(mBeforeNews.getStories());
                mZhihuAdapter.setData(stories);
                mZhihuAdapter.notifyDataSetChanged();

                //加载更多完成
                if(dataLoadStateListener != null) {
                    dataLoadStateListener.dataIsLoaded();
                }
            }

            @Override
            public void onFailure(Call<NewsTimeLine> call, Throwable t) {

            }
        });
    }

    public void getDetailNews(String id) {
        Call<News> detailNews = mZhihuApi.getDetailNews(id);
        detailNews.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("news", response.body());
                mContext.startActivity(intent);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
