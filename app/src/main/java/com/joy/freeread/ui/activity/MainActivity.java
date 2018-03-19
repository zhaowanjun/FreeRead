package com.joy.freeread.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.joy.freeread.R;
import com.joy.freeread.ui.adapter.MyFragmentPagerAdapter;
import com.joy.freeread.ui.base.BaseActivity;
import com.joy.freeread.ui.base.BaseFragment;
import com.joy.freeread.ui.fragment.GankFragment;
import com.joy.freeread.ui.fragment.VideoFragment;
import com.joy.freeread.ui.fragment.ZhiHuFragment;

import java.util.ArrayList;

import butterknife.Bind;
public class MainActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private ArrayList<BaseFragment> mFragmentList;
    private MyFragmentPagerAdapter mAdapter;
    private final int ZHIHU = 0;
    private final int GANK = 1;
    private final int VIDEO = 2;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new ZhiHuFragment());
        mFragmentList.add(new GankFragment());
        mFragmentList.add(new VideoFragment());
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.ZhihuBlue));
        ChangeTabColor();

    }

    /**
     * 添加监听，根据选中不同tab改变颜色
     */
    private void ChangeTabColor() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case ZHIHU:
                        mTabLayout.setBackgroundColor(getResources().getColor(R.color.ZhihuBlue));
                        break;
                    case GANK:
                        mTabLayout.setBackgroundColor(getResources().getColor(R.color.GankGreen));
                        break;
                    case VIDEO:
                        mTabLayout.setBackgroundColor(getResources().getColor(R.color.VideoPink));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
