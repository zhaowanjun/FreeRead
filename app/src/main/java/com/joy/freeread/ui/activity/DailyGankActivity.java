package com.joy.freeread.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.joy.freeread.R;
import com.joy.freeread.bean.gank.DailyGankBean;
import com.joy.freeread.ui.adapter.DailyGankAdapter;
import com.joy.freeread.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/10.
 */
public class DailyGankActivity extends BaseActivity {
    @Bind(R.id.daily_recyclerview)
    RecyclerView mDailyRecyclerview;

    @Override
    protected void initView() {
        ArrayList<MultiItemEntity> multiItemEntities = new ArrayList<>();
        DailyGankBean dailyGankBean = (DailyGankBean) getIntent().getSerializableExtra("dailyGankBean");
        System.out.println("==========="+dailyGankBean);
        DailyGankBean.ResultsBean resultsBean = dailyGankBean.getResults();
        multiItemEntities.add(resultsBean);
        DailyGankAdapter dailyGankAdapter = new DailyGankAdapter(multiItemEntities);
        mDailyRecyclerview.setAdapter(dailyGankAdapter);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_daily_gank;
    }
}
