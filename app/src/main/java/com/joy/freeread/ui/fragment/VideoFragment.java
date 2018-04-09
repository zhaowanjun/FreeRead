package com.joy.freeread.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.freeread.R;
import com.joy.freeread.bean.video.DataBean;
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
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
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
        initRefreshListener();
        initLoadMoreListener();
        initLoadCompletedListener();
        initItemClickListener();
    }

    private void initRefreshListener() {
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.VideoPink));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mVideoPresenter.getFirstPage();
                    }
                }, 1000);

                initLoadCompletedListener();
            }
        });
    }

    private void initLoadMoreListener() {
        mVideoPresenter.setOnLoadMoreListener(new VideoPresenter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mVideoPresenter.getNextPage();
            }
        });

    }

    private void initLoadCompletedListener() {
        mVideoPresenter.setOnLoadCompletedListener(new VideoPresenter.OnLoadCompletedListener() {
            @Override
            public void onLoadCompleted() {
                mRefresh.setRefreshing(false);
            }
        });
    }

    private void loadData() {
        mVideoAdapter = new VideoAdapter(getContext());
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerview.setHasFixedSize(true);

        mVideoPresenter = new VideoPresenter(mVideoAdapter, getContext(), mRecyclerview);
        mRecyclerview.setAdapter(mVideoAdapter);

        mRefresh.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mVideoPresenter.getFirstPage();
            }
        }, 1000);
    }

    private void initItemClickListener() {
        mVideoAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataBean data) {
                //打开视频播放页
                mVideoPresenter.openVideoPlayer(data);
            }
        });
    }

    @Override
    public String getName() {
        return "视频";
    }

}
