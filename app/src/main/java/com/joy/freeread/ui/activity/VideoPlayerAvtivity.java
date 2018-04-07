package com.joy.freeread.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.joy.freeread.R;
import com.joy.freeread.ui.base.BaseActivity;
import com.joy.freeread.ui.view.VideoController;

import butterknife.Bind;

/**
 * Created by admin on 2018/4/2.
 */
public class VideoPlayerAvtivity extends BaseActivity {
    @Bind(R.id.video_view)
    VideoView mVideoView;
    @Bind(R.id.video_cover)
    ImageView mVideoCover;
    @Bind(R.id.video_frame)
    FrameLayout mVideoFrame;
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
    private int mCurrentPosition;
    private String mFeedUrl;
    private int mVideoPortraitHeight;
    private ViewGroup.LayoutParams mVideoFramelayoutParams;
    private Button mBtnSwitch;

    @Override
    protected void initView() {
        getIntentData();
        initVideoInfo();
        initVideoPlayer();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mFeedUrl = intent.getStringExtra("feedUrl");
        mPlayUrl = intent.getStringExtra("playUrl");
        mBlurred = intent.getStringExtra("blurred");
        mTitle = intent.getStringExtra("title");
        mSlogan = intent.getStringExtra("slogan");
        mDescription = intent.getStringExtra("description");
    }

    private void initVideoInfo() {
        //添加模糊背景
        Glide.with(this)
                .load(mBlurred)
                .into(mIvBackground);

        //添加视频标题
        mTvTitle.setText(mTitle);
        //添加视频标语
        mTvSlogan.setText(mSlogan);
        //添加视频描述
        mTvDescription.setText(mDescription);
    }

    private void initVideoPlayer() {
        mVideoFramelayoutParams = mVideoFrame.getLayoutParams();
        //储存竖屏时视频的高度
        mVideoPortraitHeight = mVideoFramelayoutParams.height;
        //添加视频封面图
        Glide.with(this)
                .load(mFeedUrl)
                .into(mVideoCover);
        mVideoView.setVideoPath(mPlayUrl);
        VideoController videoController = new VideoController
                (this, this, mVideoView, mVideoFramelayoutParams, mVideoPortraitHeight);
        mBtnSwitch = (Button) videoController.findViewById(R.id.btn_switch);
        mVideoView.setMediaController(videoController);
        //监听视频准备完成
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoCover.setVisibility(View.GONE);
                mVideoView.start();
            }
        });

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_video_player;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
        mCurrentPosition = mVideoView.getCurrentPosition();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mVideoView.seekTo(mCurrentPosition);
        mVideoView.start();
    }

    /**
     * 监听返回键
     */
    @Override
    public void onBackPressed() {
        //如果是横屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            mOnBackPressedListener.backpressed();
            return;
        }
        super.onBackPressed();
    }

    //返回按钮监听接口
    private OnBackPressedListener mOnBackPressedListener;
    public interface OnBackPressedListener {
        void backpressed();
    }
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.mOnBackPressedListener = onBackPressedListener;
    }
}
