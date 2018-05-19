package com.joy.freeread.ui.activity;

import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.joy.freeread.R;
import com.joy.freeread.ui.base.BaseActivity;
import com.tobiasrohloff.view.NestedScrollWebView;

import butterknife.Bind;

/**
 * Created by admin on 2018/5/19.
 */
public class GankWebActivity extends BaseActivity {
    @Bind(R.id.gank_web_view)
    NestedScrollWebView mGankWebView;
    @Bind(R.id.web_toolbar)
    Toolbar mWebToolbar;
    private String mTitle;

    @Override
    protected void initView() {
        initToolbar();
        initWebView();
    }

    private void initToolbar() {
        mTitle = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        mWebToolbar.setSubtitle(desc);

        //获取subtitle对应的控件，并设置跑马灯效果
        final TextView subTitleView = (TextView) mWebToolbar.getChildAt(0);
        subTitleView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        subTitleView.setMarqueeRepeatLimit(-1);
        subTitleView.setFocusable(true);
        subTitleView.setFocusableInTouchMode(true);
        subTitleView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean b = subTitleView.requestFocus();
                }

            }
        });
        setSupportActionBar(mWebToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mWebToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initWebView() {
        WebSettings settings = mGankWebView.getSettings();
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mGankWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }

                return true;
            }
        });

        mGankWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    mWebToolbar.setTitle(mTitle + "(" + progress + ")");
                } else if (newProgress == 100) {
                    mWebToolbar.setTitle(mTitle);
                }
            }
        });

        String url = getIntent().getStringExtra("url");
        mGankWebView.loadUrl(url);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_gank_web;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mGankWebView.canGoBack()) {
            mGankWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGankWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGankWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGankWebView.removeAllViews();
        mGankWebView.destroy();
    }

}
