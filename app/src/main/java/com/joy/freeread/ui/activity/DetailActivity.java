package com.joy.freeread.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joy.freeread.R;
import com.joy.freeread.bean.zhihu.News;
import com.joy.freeread.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhaowanjun on 2018/2/26.
 */

public class DetailActivity extends BaseActivity {

    @Bind(R.id.iv_web_img)
    ImageView mIvWebImg;
    @Bind(R.id.tv_img_title)
    TextView mTvImgTitle;
    @Bind(R.id.tv_img_source)
    TextView mTvImgSource;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.web_view)
    WebView mWebView;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setWebView();
    }

    private void setWebView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        News news = (News) bundle.get("news");

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\""+news.getCss()[0]+"\"/>\n" +
                "</head>";
        String img = "<div class=\"headline\">";
        String html =head + news.getBody().replace(img," ");
        mWebView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
        Glide.with(this).load(news.getImage()).centerCrop().into(mIvWebImg);

        mTvImgTitle.setText(news.getTitle());
        mTvImgSource.setText(news.getImage_source());
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_web_view;
    }

}
