package com.joy.freeread.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joy.freeread.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private ArrayList<BaseFragment> mFragmentList;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
        mTitles = new String[fragmentList.size()];

        for (int i = 0; i < mTitles.length; i++) {
            mTitles[i] = fragmentList.get(i).getName();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
