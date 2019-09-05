package com.joy.freeread.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.joy.freeread.R;
import com.joy.freeread.ui.base.BaseActivity;
import com.joy.freeread.ui.view.VideoController;

import java.net.URL;

import butterknife.Bind;

/**
 * Created by admin on 2018/4/2.
 */
public class VideoPlayerAvtivity extends BaseActivity {
    @Bind(R.id.video_view)
    VideoView mVideoView;
    @Bind(R.id.video_cover)
    ImageView mVideoCover;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
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
    //设置文字颜色的what标识
    private final int SET_TEXT_COLOR = 0x01;
    //设置模糊背景的what标识
    private final int SET_BLUR_BG = 0x02;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case SET_TEXT_COLOR: //设置文字颜色并显示
                    int bodyTextColor = (int) msg.obj;
                    mTvTitle.setTextColor(bodyTextColor);
                    mTvSlogan.setTextColor(bodyTextColor);
                    mTvDescription.setTextColor(bodyTextColor);
                    mTvTitle.setVisibility(View.VISIBLE);
                    mTvSlogan.setVisibility(View.VISIBLE);
                    mTvDescription.setVisibility(View.VISIBLE);
                    break;
                case SET_BLUR_BG: //设置模糊背景图片
                    Bitmap bitmap = (Bitmap) msg.obj;
                    mIvBackground.setImageBitmap(bitmap);
                    break;
                default:
                    break;
            }
        }
    };

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
        //设置视频高度为宽度的9/16
        int width = getWindowManager().getDefaultDisplay().getWidth();
        mVideoFrame.getLayoutParams().height = width*9/16;

        //添加模糊背景
//        Glide.with(this)
//                .load(mBlurred)
//                .into(mIvBackground);

        //添加视频标题
        mTvTitle.setText(mTitle);
        //添加视频标语
        mTvSlogan.setText(mSlogan);
        //添加视频描述
        mTvDescription.setText(mDescription);

        //获取背景bitmap，根据背景色动态调整最合适的文字颜色
        adaptTextColor();
    }

    /**
     * 根据背景图片动态改变文字颜色
     */
    private void adaptTextColor() {
        //获取Bitmap需要开子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = getBitmap(mBlurred);
                if(bitmap != null) {
                    //添加模糊背景
                    Message message = handler.obtainMessage(SET_BLUR_BG, bitmap);
                    handler.sendMessage(message);

                    Palette.Builder builder = new Palette.Builder(bitmap);
                    builder.generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            //通过Palette来获取对应的色调
                            final Palette.Swatch swatch = palette.getDominantSwatch();
                            if(swatch != null) {
                                int bodyTextColor = swatch.getBodyTextColor();
                                //将颜色设置给相应的组件
                                Message message = handler.obtainMessage(SET_TEXT_COLOR, bodyTextColor);
                                handler.sendMessage(message);
                            }

                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 传入一个url字符串，返回一个Bitmap（须在子线程中调用）
     * @param urlString
     * @return
     */
    private Bitmap getBitmap(String urlString) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(mBlurred);
            bitmap = BitmapFactory.decodeStream(url.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
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
                (VideoPlayerAvtivity.this, VideoPlayerAvtivity.this, mVideoView, mVideoFramelayoutParams, mVideoPortraitHeight);
        mVideoView.setMediaController(videoController);
        //监听视频准备完成
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoCover.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
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
