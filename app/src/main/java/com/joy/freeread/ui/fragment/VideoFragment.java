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

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        
        VideoPresenter videoPresenter = new VideoPresenter(mVideoAdapter, getContext());
        mRecyclerview.setAdapter(mVideoAdapter);
        videoPresenter.getFirstPage();
    }


    @Override
    public String getName() {
        return "视频";
    }

}
