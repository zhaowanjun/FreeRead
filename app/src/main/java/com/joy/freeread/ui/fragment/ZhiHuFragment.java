package com.joy.freeread.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.joy.freeread.R;
import com.joy.freeread.bean.zhihu.TopStories;
import com.joy.freeread.ui.adapter.ZhihuAdapter;
import com.joy.freeread.ui.base.BaseFragment;
import com.joy.freeread.ui.presenter.ZhihuPresenter;
import com.joy.freeread.ui.view.LoadMoreListView;

import butterknife.Bind;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public class ZhiHuFragment extends BaseFragment {

    @Bind(R.id.lv_zhihu)
    LoadMoreListView mLvZhihu;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    private ZhihuAdapter mZhihuAdapter;
    private ZhihuPresenter mZhihuPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView(View view) {

        initRefreshListener();
        initLoadMoreListener();
        loadData();
        initDataLoadStateListener();
        initSLClickListener();
        initLvClickListener();
    }

    private void initSLClickListener() {
        mLvZhihu.setOnHeadItemClickListener(new LoadMoreListView.OnHeadItemClickListener() {
            @Override
            public void headItemClick(TopStories data, int position) {
                //获取相应的新闻详情
                mZhihuPresenter.getDetailNews(data.getId());
            }
        });
    }

    private void initLvClickListener() {
        mLvZhihu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取相应的新闻详情
                mZhihuPresenter.getDetailNews(String.valueOf(id));
            }
        });
    }

    private void initDataLoadStateListener() {
        //监听数据加载完成
        mZhihuPresenter.setDataLoadStateListener(new ZhihuPresenter.DataLoadStateListener() {
            @Override
            public void dataIsLoaded() {
                mRefresh.setRefreshing(false);
            }
        });
    }

    private void loadData() {
        mZhihuAdapter = new ZhihuAdapter(getContext(), mLvZhihu);
        mZhihuPresenter = new ZhihuPresenter(mZhihuAdapter, getContext());
        mLvZhihu.setAdapter(mZhihuAdapter);

        mRefresh.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mZhihuPresenter.getLatestNews();
            }
        }, 2000);

    }

    private void initRefreshListener() {
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.ZhihuBlue));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mZhihuPresenter.getLatestNews();
                    }
                }, 2000);

                initDataLoadStateListener();
            }
        });
    }

    private void initLoadMoreListener() {
        mLvZhihu.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //加载更多
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mZhihuPresenter.getMoreNews();
                    }
                }, 2000);


                //加载更多完成
                mZhihuPresenter.setDataLoadStateListener(new ZhihuPresenter.DataLoadStateListener() {
                    @Override
                    public void dataIsLoaded() {
                        mLvZhihu.setLoadCompleted();
                    }
                });
            }
        });
    }



    @Override
    public String getName() {
        return "知乎";
    }

}
