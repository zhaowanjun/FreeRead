package com.joy.freeread.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.joy.freeread.R;
import com.joy.freeread.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/19.
 */
public class GankWebActivity extends BaseActivity {
    @Bind(R.id.gank_web_view)
    WebView mGankWebView;

    @Override
    protected void initView() {
        WebSettings settings = mGankWebView.getSettings();
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mGankWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if( url.startsWith("http:") || url.startsWith("https:") ) {
                    return false;
                }

                return true;
            }
        });
        settings.setJavaScriptEnabled(true);
        String url = getIntent().getStringExtra("url");
        mGankWebView.loadUrl(url);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_gank_web;
    }

}
