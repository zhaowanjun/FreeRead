package com.joy.freeread.ui.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.joy.freeread.R;
import com.joy.freeread.ui.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by admin on 2018/4/2.
 */
public class VideoPlayerAvtivity extends BaseActivity {
    @Bind(R.id.video_view)
    VideoView mVideoView;
    @Bind(R.id.iv_background)
    ImageView mIvBackground;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_description)
    TextView mTvDescription;
    @Bind(R.id.tv_slogan)
    TextView mTvSlogan;

    private String mPlayUrl;
    private String mBlurred;
    private String mTitle;
    private String mSlogan;
    private String mDescription;

    @Override
    protected void initView() {
        getIntentData();
        initVideoPlayer();
        initVideoInfo();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mPlayUrl = intent.getStringExtra("playUrl");
        mBlurred = intent.getStringExtra("blurred");
        mTitle = intent.getStringExtra("title");
        mSlogan = intent.getStringExtra("slogan");
        mDescription = intent.getStringExtra("description");
    }

    private void initVideoPlayer() {
        mVideoView.setVideoPath(mPlayUrl);
        mVideoView.start();
    }

    private void initVideoInfo() {
        //添加模糊背景
        Glide.with(this)
                .load(mBlurred)
                .centerCrop()
                .into(mIvBackground);

        //添加视频标题
        mTvTitle.setText(mTitle);
        //添加视频标语
        mTvSlogan.setText(mSlogan);
        //添加视频描述
        mTvDescription.setText(mDescription);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_video_player;
    }

}
