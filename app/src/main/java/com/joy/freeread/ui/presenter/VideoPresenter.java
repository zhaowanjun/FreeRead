package com.joy.freeread.ui.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.joy.freeread.bean.video.IssueListBean;
import com.joy.freeread.bean.video.ItemListBean;
import com.joy.freeread.bean.video.Videos;
import com.joy.freeread.ui.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2018/3/30.
 */
public class VideoPresenter extends BasePresenter {

    private final VideoAdapter mVideoAdapter;
    private final Context mContext;

    public VideoPresenter(VideoAdapter videoAdapter, Context context) {
        mVideoAdapter = videoAdapter;
        mContext = context;
    }

    public void getFirstPage() {
        Call<Videos> firstPage = mVideoApi.getFirstPage();
        firstPage.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videoBean = response.body();
                List<IssueListBean> issueList = videoBean.getIssueList();

                List<ItemListBean> data = new ArrayList<>();
                for (IssueListBean issueListBean : issueList) {
                    List<ItemListBean> itemList = issueListBean.getItemList();
                    for (int i = 1; i < itemList.size(); i++) {
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
}
