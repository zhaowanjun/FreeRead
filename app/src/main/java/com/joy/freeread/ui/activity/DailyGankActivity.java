package com.joy.freeread.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.joy.freeread.R;
import com.joy.freeread.bean.gank.AndroidBean;
import com.joy.freeread.bean.gank.AppBean;
import com.joy.freeread.bean.gank.DailyGankBean;
import com.joy.freeread.bean.gank.IOSBean;
import com.joy.freeread.bean.gank.ResultsBean;
import com.joy.freeread.bean.gank.TitleItem;
import com.joy.freeread.bean.gank.休息视频Bean;
import com.joy.freeread.bean.gank.前端Bean;
import com.joy.freeread.bean.gank.拓展资源Bean;
import com.joy.freeread.bean.gank.瞎推荐Bean;
import com.joy.freeread.bean.gank.福利Bean;
import com.joy.freeread.ui.adapter.DailyDankAdapter;
import com.joy.freeread.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by admin on 2018/5/10.
 */
public class DailyGankActivity extends BaseActivity {
    @Bind(R.id.daily_recyclerview)
    RecyclerView mDailyRecyclerview;
    private List<MultiItemEntity> mResultsList;

    @Override
    public int getContentViewId() {
        return R.layout.activity_daily_gank;
    }

    @Override
    protected void initView() {
        setData();
        initAdapter();
    }

    private void setData() {
        mResultsList = new ArrayList<>();
        DailyGankBean dailyGankBean = (DailyGankBean) getIntent().getSerializableExtra("dailyGankBean");
        ResultsBean results = dailyGankBean.getResults();

        List<AndroidBean> android = results.getAndroid();
        if(android != null) {
            TitleItem titleItem = new TitleItem("Android");
            for (AndroidBean androidBean : android) {
                titleItem.addSubItem(androidBean);
            }
            mResultsList.add(titleItem);
        }

        List<AppBean> app = results.getApp();
        if(app != null) {
            TitleItem titleItem = new TitleItem("App");
            for (AppBean appBean : app) {
                titleItem.addSubItem(appBean);
            }
            mResultsList.add(titleItem);
        }

        List<IOSBean> ios = results.getIOS();
        if(ios != null) {
            TitleItem titleItem = new TitleItem("IOS");
            for (IOSBean iosBean : ios) {
                titleItem.addSubItem(iosBean);
            }
            mResultsList.add(titleItem);
        }

        List<休息视频Bean> 休息视频 = results.get休息视频();
        if(休息视频 != null) {
            TitleItem titleItem = new TitleItem("休息视频");
            for (休息视频Bean 休息视频Bean : 休息视频) {
                titleItem.addSubItem(休息视频Bean);
            }
            mResultsList.add(titleItem);
        }

        List<前端Bean> 前端 = results.get前端();
        if(前端 != null) {
            TitleItem titleItem = new TitleItem("前端");
            for (前端Bean 前端Bean : 前端) {
                titleItem.addSubItem(前端Bean);
            }
            mResultsList.add(titleItem);
        }

        List<拓展资源Bean> 拓展资源 = results.get拓展资源();
        if(拓展资源 != null) {
            TitleItem titleItem = new TitleItem("拓展资源");
            for (拓展资源Bean 拓展资源Bean : 拓展资源) {
                titleItem.addSubItem(拓展资源Bean);
            }
            mResultsList.add(titleItem);
        }

        List<瞎推荐Bean> 瞎推荐 = results.get瞎推荐();
        if(瞎推荐 != null) {
            TitleItem titleItem = new TitleItem("瞎推荐");
            for (瞎推荐Bean 瞎推荐Bean : 瞎推荐) {
                titleItem.addSubItem(瞎推荐Bean);
            }
            mResultsList.add(titleItem);
        }

//        List<福利Bean> 福利 = results.get福利();
//        if(福利 != null) {
//            TitleItem titleItem = new TitleItem("福利");
//            for (福利Bean 福利Bean : 福利) {
//                titleItem.addSubItem(福利Bean);
//            }
//            mResultsList.add(titleItem);
//        }
    }


    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mDailyRecyclerview.setLayoutManager(linearLayoutManager);
        DailyDankAdapter dailyDankAdapter = new DailyDankAdapter(mResultsList);
        mDailyRecyclerview.setAdapter(dailyDankAdapter);
    }

}
