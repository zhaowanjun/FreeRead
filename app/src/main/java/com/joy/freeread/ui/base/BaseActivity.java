package com.joy.freeread.ui.base;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by zhaowanjun on 2017/11/22.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * @return  返回布局layout的id
     */
    public abstract int getContentViewId();

}
