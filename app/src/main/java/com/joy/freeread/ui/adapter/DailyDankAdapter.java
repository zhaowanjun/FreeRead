package com.joy.freeread.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.joy.freeread.R;
import com.joy.freeread.bean.gank.AndroidBean;
import com.joy.freeread.bean.gank.AppBean;
import com.joy.freeread.bean.gank.IOSBean;
import com.joy.freeread.bean.gank.TitleItem;
import com.joy.freeread.bean.gank.休息视频Bean;
import com.joy.freeread.bean.gank.前端Bean;
import com.joy.freeread.bean.gank.拓展资源Bean;
import com.joy.freeread.bean.gank.瞎推荐Bean;
import com.joy.freeread.ui.activity.DetailActivity;
import com.joy.freeread.ui.activity.GankWebActivity;
import com.joy.freeread.ui.view.WrapWidthImageView;
import com.joy.freeread.utils.ScreenUtil;

import java.util.List;

/**
 * Created by admin on 2018/5/15.
 */
public class DailyDankAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, DailyDankAdapter.MyViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DailyDankAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.view_expand0);
        addItemType(1, R.layout.view_expand1);
    }

    @Override
    protected void convert(final MyViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case 0:
                final TitleItem item0 = (TitleItem) item;
                helper.setText(R.id.text, item0.getTitle());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (item0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case 1:
                String desc = null;
                String url = null;
                List<String> imageUrls = null;

                if (item.getClass() == AndroidBean.class) {
                    AndroidBean item1 = (AndroidBean) item;
                    desc = item1.getDesc();
                    url = item1.getUrl();
                    imageUrls = item1.getImages();

                } else if (item.getClass() == AppBean.class) {
                    AppBean item1 = (AppBean) item;
                    desc = item1.getDesc();
                    url = item1.getUrl();
                    imageUrls = item1.getImages();

                } else if (item.getClass() == IOSBean.class) {
                    IOSBean item1 = (IOSBean) item;
                    desc = item1.getDesc();
                    url = item1.getUrl();
                    imageUrls = item1.getImages();

                } else if (item.getClass() == 休息视频Bean.class) {
                    休息视频Bean item1 = (休息视频Bean) item;
                    url = item1.getUrl();
                    desc = item1.getDesc();

                } else if (item.getClass() == 前端Bean.class) {
                    前端Bean item1 = (前端Bean) item;
                    desc = item1.getDesc();
                    url = item1.getUrl();

                } else if (item.getClass() == 拓展资源Bean.class) {
                    拓展资源Bean item1 = (拓展资源Bean) item;
                    desc = item1.getDesc();
                    url = item1.getUrl();

                } else if (item.getClass() == 瞎推荐Bean.class) {
                    瞎推荐Bean item1 = (瞎推荐Bean) item;
                    desc = item1.getDesc();
                    url = item1.getUrl();
                }
//                } else if (item.getClass() == 福利Bean.class) {
//                    福利Bean item1 = (福利Bean) item;
//                    desc = item1.getDesc();
//                }

                helper.setText(R.id.text, desc);
                helper.setGifDrawable(mContext, R.id.ll_image_container, imageUrls);

                final String u = url;
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击条目，跳转webview界面
                        Intent intent = new Intent(mContext, GankWebActivity.class);
                        intent.putExtra("url", u);
                        mContext.startActivity(intent);
                    }
                });
                break;
        }
    }

    public static class MyViewHolder extends BaseViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }

        public BaseViewHolder setGifDrawable(Context context, int viewId, List<String> imageUrls) {
            LinearLayout linearLayout = getView(viewId);
            linearLayout.removeAllViews();
            if(imageUrls == null) return this;

            linearLayout.setVisibility(View.VISIBLE);
            ScreenUtil screenUtil = ScreenUtil.instance(context);
            int width = screenUtil.getScreenWidth();
            int height = width * 9 / 16;
            int margin = screenUtil.dip2px(8);

            for (String imageUrl : imageUrls) {

                WrapWidthImageView imageView = new WrapWidthImageView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
                lp.setMargins(margin, 0, margin, margin);
                imageView.setLayoutParams(lp);
                Glide.with(context)
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(imageView);
                linearLayout.addView(imageView);
            }

            return this;
        }
    }
}