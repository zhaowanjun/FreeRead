package com.joy.freeread.ui.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.joy.freeread.R;
import com.joy.freeread.ui.adapter.GankAdapter;
import com.joy.freeread.ui.adapter.VideoAdapter;
import com.joy.freeread.ui.base.BaseFragment;
import com.joy.freeread.ui.presenter.GankPresenter;
import com.joy.freeread.utils.DensityUtil;
import com.joy.freeread.utils.ScreenUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public class GankFragment extends BaseFragment {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private GankAdapter mGankAdapter;
    private GankPresenter mGankPresenter;
    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView(View view) {

        loadData();
        initRefreshListener();
        initLoadMoreListener();
        initLoadCompletedListener();
        initItemClickListener();

    }

    private void loadData() {
        mGankAdapter = new GankAdapter(getContext());

        //创建瀑布流布局管理器
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE); //设置空隙处理方式为 不处理

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                int[] firstVisibleItems = null;
                int[] firstVisibleItemPositions = layoutManager.findFirstVisibleItemPositions(firstVisibleItems);
                if(firstVisibleItemPositions != null && firstVisibleItemPositions[0] == 0) {
                    if(mGankAdapter != null) {
                        mGankAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);

        mGankPresenter = new GankPresenter(getContext(), mRecyclerView, mGankAdapter);
        mRecyclerView.setAdapter(mGankAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGankPresenter.getMeiZhiData();
            }
        }, 1000);
    }

    private void initRefreshListener() {

    }

    private void initLoadMoreListener() {

    }

    private void initLoadCompletedListener() {
    }

    private void initItemClickListener() {

    }

    @Override
    public String getName() {
        return "干货";
    }

}
