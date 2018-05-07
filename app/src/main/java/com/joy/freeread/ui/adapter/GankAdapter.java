package com.joy.freeread.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.joy.freeread.R;
import com.joy.freeread.bean.gank.Meizhi;
import com.joy.freeread.utils.ScreenUtil;

import java.util.List;

/**
 * Created by zhaowanjun on 2018/5/6.
 */

public class GankAdapter extends BaseQuickAdapter<Meizhi.ResultsBean, BaseViewHolder> {

    public GankAdapter(int layoutResId, List<Meizhi.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Meizhi.ResultsBean item) {
        final ImageView mCardImage = (ImageView) holder.getConvertView().findViewById(R.id.card_image);
        TextView mCardText = (TextView) holder.getConvertView().findViewById(R.id.card_text);

        final String url = item.getUrl();
        final int width = ScreenUtil.instance(mContext).getScreenWidth() / 2;

        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .override(width, width)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        int height = (int) (((float)width)/bitmap.getWidth()*bitmap.getHeight());
                        mCardImage.getLayoutParams().height = height;
                        mCardImage.setImageBitmap(bitmap);
                    }
                });

        //文字
        String publishedAt = item.getPublishedAt();
        String date = publishedAt.split("T")[0];
        mCardText.setText(date);
    }

    public void setData(List<Meizhi.ResultsBean> meizhiList) {
        mData = meizhiList;
    }
}
