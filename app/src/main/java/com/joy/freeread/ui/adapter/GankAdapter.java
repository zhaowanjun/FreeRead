package com.joy.freeread.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.joy.freeread.R;
import com.joy.freeread.bean.gank.Meizhi;
import com.joy.freeread.ui.view.ResizeableImageView;
import com.joy.freeread.utils.ScreenUtil;

import java.util.List;

/**
 * Created by zhaowanjun on 2018/5/6.
 */

public class GankAdapter extends BaseQuickAdapter<Meizhi.ResultsBean, BaseViewHolder> {

    SparseArray<Integer> heightArray = new SparseArray<>();
    private float mWidth;
    private boolean hasGetWidth;

    public GankAdapter(int layoutResId, List<Meizhi.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Meizhi.ResultsBean item) {

        final int layoutPosition = holder.getLayoutPosition();
        final ResizeableImageView mCardImage = (ResizeableImageView) holder.getConvertView().findViewById(R.id.card_image);
        TextView mCardText = (TextView) holder.getConvertView().findViewById(R.id.card_text);
        final String url = item.getUrl();

        RequestManager requestManager = Glide.with(mContext);
        if (heightArray.get(layoutPosition) != null) {
            int height = heightArray.get(layoutPosition);
            mCardImage.getLayoutParams().height = height;
            requestManager
                    .load(url)
                    .fitCenter()
                    .into(mCardImage);
        } else {
            requestManager
                    .load(url)
                    .asBitmap()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                            if(!hasGetWidth) {
                                mWidth = mCardImage.getWidth();
                                hasGetWidth = true ;
                            }
                            int height = (int) (mWidth / bitmap.getWidth() * bitmap.getHeight());
                            heightArray.append(layoutPosition, height);
                            mCardImage.getLayoutParams().height = height;
                            mCardImage.setImageBitmap(bitmap);
                        }
                    });
        }

        //文字
        String publishedAt = item.getPublishedAt();
        String date = publishedAt.split("T")[0];
        mCardText.setText(date);
    }
}
