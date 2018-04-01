package com.joy.freeread.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.freeread.R;
import com.joy.freeread.ui.adapter.VideoAdapter;
import com.joy.freeread.ui.base.BaseFragment;
import com.joy.freeread.ui.presenter.VideoPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public class VideoFragment extends BaseFragment {


    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private VideoAdapter mVideoAdapter;
    private LinearLayoutManager mLayoutManager;
    private VideoPresenter mVideoPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view) {
        loadData();
    }

    private void loadData() {
        mVideoAdapter = new VideoAdapter(getContext());
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerview.setHasFixedSize(true);

        mVideoPresenter = new VideoPresenter(mVideoAdapter, getContext());
        mRecyclerview.setAdapter(mVideoAdapter);
        mVideoPresenter.getFirstPage();

    }


    @Override
    public String getName() {
        return "视频";
    }

}
