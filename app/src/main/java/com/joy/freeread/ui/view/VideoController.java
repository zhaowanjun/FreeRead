package com.joy.freeread.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.joy.freeread.R;
import com.joy.freeread.ui.activity.VideoPlayerAvtivity;

/**
 * Created by zhaowanjun on 2018/4/7.
 */

public class VideoController extends MediaController {

    private Button mBtnSwitch;
    private VideoPlayerAvtivity mActivity;
    private VideoView mVideoView;
    private ViewGroup.LayoutParams mVideoFramelayoutParams;
    private int mVideoPortraitHeight;


    public VideoController(Context context, Activity activity, VideoView videoView,
                           ViewGroup.LayoutParams videoFramelayoutParams, int videoPortraitHeight) {
        super(context);
        mActivity = (VideoPlayerAvtivity) activity;
        mVideoView = videoView;
        mVideoFramelayoutParams = videoFramelayoutParams;
        mVideoPortraitHeight = videoPortraitHeight;
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);
        FrameLayout frameLayout = (android.widget.FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.button_switch_screen, null);
        mBtnSwitch = (Button) frameLayout.findViewById(R.id.btn_switch);
        addView(frameLayout);
        mBtnSwitch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreen();
            }
        });
        mActivity.setOnBackPressedListener(new VideoPlayerAvtivity.OnBackPressedListener() {
            @Override
            public void backpressed() {
                changeToProtrait();
            }
        });
    }

    private void switchScreen() {
        //全屏切换
        if (mActivity.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            changeToProtrait();
        } else {
            changeToFull();
        }
    }

    private void changeToFull() {
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mBtnSwitch.setBackgroundDrawable(getResources().getDrawable(R.drawable.out_full_screen));
        mVideoFramelayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mVideoView.getHolder().setFixedSize(mVideoView.getWidth(), mVideoView.getHeight());
        //隐藏状态栏
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void changeToProtrait() {
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mBtnSwitch.setBackgroundDrawable(getResources().getDrawable(R.drawable.full_screen));
        mVideoFramelayoutParams.height = mVideoPortraitHeight;
        mVideoView.getHolder().setFixedSize(mVideoView.getWidth(), mVideoView.getHeight());
        //显示状态栏
        mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
