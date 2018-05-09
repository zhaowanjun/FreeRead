package com.joy.freeread.ui.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.joy.freeread.R;
import com.joy.freeread.bean.gank.Meizhi;
import com.joy.freeread.ui.adapter.GankAdapter;
import com.joy.freeread.ui.base.BaseFragment;
import com.joy.freeread.ui.base.BasePresenter;
import com.joy.freeread.ui.presenter.GankPresenter;
import com.joy.freeread.utils.DensityUtil;
import com.joy.freeread.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public class GankFragment extends BaseFragment {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    private GankAdapter mGankAdapter;
    private GankPresenter mGankPresenter;
    private StaggeredGridLayoutManager mLayoutManager;
    private List<Meizhi.ResultsBean> data = new ArrayList<>();

    //定义数据加载方式 0刷新 ；1更多
    private final int REFRESH = 0;
    private final int MORE = 1;

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
        int width = (ScreenUtil.instance(getContext()).getScreenWidth()) / 2;
        mGankAdapter = new GankAdapter(R.layout.gank_item, data);

        //创建瀑布流布局管理器
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE); //设置空隙处理方式为 不处理
        mRecyclerView.setLayoutManager(mLayoutManager);

        mGankPresenter = new GankPresenter(getContext(), mRecyclerView, mGankAdapter);
        mRecyclerView.setAdapter(mGankAdapter);

        mRefresh.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGankPresenter.getMeiZhiData(REFRESH);
            }
        }, 1000);

        View view = new View(getActivity());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(1, DensityUtil.dp2px(getActivity(), 5));
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(Color.WHITE);
        mGankAdapter.addHeaderView(view);

    }

    private void initRefreshListener() {
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.GankGreen));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mGankPresenter.getMeiZhiData(MORE);
                    }
                }, 1000);

                initLoadCompletedListener();
            }
        });
    }

    private void initLoadMoreListener() {

    }

    private void initLoadCompletedListener() {
        mGankPresenter.setDataLoadStateListener(new BasePresenter.DataLoadStateListener() {
            @Override
            public void dataIsLoaded() {
                mRefresh.setRefreshing(false);
            }
        });
    }

    private void initItemClickListener() {

    }

    @Override
    public String getName() {
        return "干货";
    }

}
