package com.joy.freeread.ui.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.joy.freeread.bean.gank.DayData;
import com.joy.freeread.bean.gank.GankDay;
import com.joy.freeread.bean.gank.Meizhi;
import com.joy.freeread.ui.adapter.GankAdapter;
import com.joy.freeread.ui.base.BasePresenter;

import java.util.ArrayList;
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
    private List<String> mDateList;
    private List<Meizhi.ResultsBean> mMeizhiList = new ArrayList<>();
    //页码
    private int page = 0;
    //定义数据加载方式 0刷新 ；1更多
    private final int REFRESH = 0;
    private final int MORE = 1;

    public GankPresenter(Context context, RecyclerView recyclerView, GankAdapter gankAdapter) {
        mContext = context;
        mRecyclerView = recyclerView;
        mGankAdapter = gankAdapter;
    }

    public void getGankDay() {
        Call<GankDay> gankDay = mGankApi.getGankDay();
        gankDay.enqueue(new Callback<GankDay>() {
            @Override
            public void onResponse(Call<GankDay> call, Response<GankDay> response) {
                mDateList = response.body().getResults();
            }

            @Override
            public void onFailure(Call<GankDay> call, Throwable t) {

            }
        });
    }

    public void getDayData(int position) {
        String result = mDateList.get(position);
        String date = result.replace('-', '/');
        Call<DayData> dayData = mGankApi.getDayData(date);
    }

    public void getMeiZhiData(final int loadWay) {
        if(loadWay == REFRESH) {
            page = 0;
        }
        final Call<Meizhi> meiZhiCall = mGankApi.getMeiZhi(page++);
        meiZhiCall.enqueue(new Callback<Meizhi>() {
            @Override
            public void onResponse(Call<Meizhi> call, Response<Meizhi> response) {
                List<Meizhi.ResultsBean> results = response.body().getResults();
                mMeizhiList.clear();
                mMeizhiList.addAll(results);
                if(loadWay == REFRESH) {
                    mGankAdapter.setNewData(mMeizhiList);
                } else {
                    mGankAdapter.addData(mMeizhiList);
                }

                dataLoadStateListener.dataIsLoaded();
            }

            @Override
            public void onFailure(Call<Meizhi> call, Throwable t) {

            }
        });
    }
}
