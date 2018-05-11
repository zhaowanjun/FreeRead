package com.joy.freeread.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.joy.freeread.bean.gank.DailyGankBean;
import com.joy.freeread.bean.gank.MeizhiBean;
import com.joy.freeread.ui.activity.DailyGankActivity;
import com.joy.freeread.ui.activity.VideoPlayerAvtivity;
import com.joy.freeread.ui.adapter.GankAdapter;
import com.joy.freeread.ui.base.BasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2018/4/23.
 */
public class GankPresenter extends BasePresenter {

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private final GankAdapter mGankAdapter;
    //页码
    private int page = 1;
    //定义数据加载方式 0刷新 ；1更多
    private final int REFRESH = 0;
    private final int MORE = 1;

    public GankPresenter(Context context, RecyclerView recyclerView, GankAdapter gankAdapter) {
        mContext = context;
        mRecyclerView = recyclerView;
        mGankAdapter = gankAdapter;
    }

    public void getMeiZhiData(final int loadWay) {
        if(loadWay == REFRESH) {
            page = 1;
        }
        final Call<MeizhiBean> meiZhiCall = mGankApi.getMeiZhi(page++);
        meiZhiCall.enqueue(new Callback<MeizhiBean>() {
            @Override
            public void onResponse(Call<MeizhiBean> call, Response<MeizhiBean> response) {
                List<MeizhiBean.ResultsBean> results = response.body().getResults();
                if(loadWay == REFRESH) {
                    mGankAdapter.setNewData(results);
                    dataLoadStateListener.dataIsLoaded();
                } else {
                    if(results != null && results.size() > 0) {
                        mGankAdapter.addData(results);
                        mGankAdapter.loadMoreComplete();
                    } else {
                        mGankAdapter.loadMoreEnd();
                    }
                }
            }

            @Override
            public void onFailure(Call<MeizhiBean> call, Throwable t) {

            }
        });
    }

    public void openDailyGank(String date) {
        date = date.replace('-', '/');
        mContext.startActivity(new Intent(mContext, DailyGankActivity.class));
        Call<DailyGankBean> dailyGank = mGankApi.getDailyGank(date);
        dailyGank.enqueue(new Callback<DailyGankBean>() {
            @Override
            public void onResponse(Call<DailyGankBean> call, Response<DailyGankBean> response) {
                DailyGankBean dailyGankBean = response.body();
                Intent intent = new Intent(mContext, DailyGankActivity.class);
                intent.putExtra("dailyGankBean", dailyGankBean);
                mContext.startActivity(intent);
            }

            @Override
            public void onFailure(Call<DailyGankBean> call, Throwable t) {

            }
        });
    }
}
