package com.joy.freeread.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joy.freeread.bean.video.DataBean;
import com.joy.freeread.bean.video.IssueListBean;
import com.joy.freeread.bean.video.ItemListBean;
import com.joy.freeread.bean.video.Videos;
import com.joy.freeread.ui.activity.VideoPlayerAvtivity;
import com.joy.freeread.ui.adapter.VideoAdapter;
import com.joy.freeread.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2018/3/30.
 */
public class VideoPresenter extends BasePresenter {

    private VideoAdapter mVideoAdapter;
    private Context mContext;
    private RecyclerView mRecyclerview;
    List<ItemListBean> data = new ArrayList<>();
    HashMap<String, String> paramMap = new HashMap<>();

    public VideoPresenter(VideoAdapter videoAdapter, Context context, RecyclerView recyclerview) {
        mVideoAdapter = videoAdapter;
        mContext = context;
        mRecyclerview = recyclerview;

        initOnScrollListener();
    }

    private void initOnScrollListener() {
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerview.getLayoutManager();
                //获取最后一个索引值
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(data.size() > 0
                        && newState == mRecyclerview.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition == data.size()-1) {
                    mOnLoadMoreListener.onLoadMore();
                }
            }
        });
    }

    public void getFirstPage() {
        Call<Videos> firstPage = mVideoApi.getFirstPage();
        firstPage.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videoBean = response.body();
                //得到Url链接中的参数对
                getUrlParamMap(videoBean);
                data.clear();
                for (IssueListBean issueListBean : videoBean.getIssueList()) {
                    List<ItemListBean> itemList = issueListBean.getItemList();
                    for (int i = 2; i < itemList.size(); i++) {
                        data.add(itemList.get(i));
                    }
                }

                mVideoAdapter.setData(data);
                mVideoAdapter.notifyDataSetChanged();
                dataLoadStateListener.dataIsLoaded();
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });
    }

    private void getUrlParamMap(Videos videoBean) {
        paramMap.clear();
        String nextPageUrl = videoBean.getNextPageUrl();
        String nextPagePath = nextPageUrl.split("[?]")[1];
        String[] paramsArr = nextPagePath.split("&");
        for (String params : paramsArr) {
            String[] paramKv = params.split("=");
            paramMap.put(paramKv[0], paramKv[1]);
        }
    }

    public void getNextPage() {
        Call<Videos> nextPage = mVideoApi.getNextPage(paramMap.get("date"), paramMap.get("num"));
        nextPage.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videoBean = response.body();
                getUrlParamMap(videoBean);
                for (IssueListBean issueListBean : videoBean.getIssueList()) {
                    List<ItemListBean> itemList = issueListBean.getItemList();
                    for (int i = 2; i < itemList.size(); i++) {
                        data.add(itemList.get(i));
                    }
                }

                mVideoAdapter.setData(data);
                mVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });
    }

    public void openVideoPlayer(DataBean data) {
        Intent intent = new Intent(mContext, VideoPlayerAvtivity.class);
        intent.putExtra("feedUrl", data.getCover().getFeed());
        intent.putExtra("playUrl", data.getPlayUrl());
        intent.putExtra("blurred", data.getCover().getBlurred());
        intent.putExtra("title", data.getTitle());
        intent.putExtra("slogan", data.getSlogan());
        intent.putExtra("description", data.getDescription());
        mContext.startActivity(intent);
    }

    private OnLoadMoreListener mOnLoadMoreListener;
    public interface OnLoadMoreListener {
        void onLoadMore();
    }
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }
}
