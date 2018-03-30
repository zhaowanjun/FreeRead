package com.joy.freeread.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joy.freeread.R;
import com.joy.freeread.bean.video.ItemListBean;

import java.util.List;

/**
 * Created by admin on 2018/3/30.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<ItemListBean> mData;
    private Context mContext;

    public VideoAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ItemListBean> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View inflate = View.inflate(mContext, R.layout.video_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //将数据填充到具体的view中
        String feedUrl = mData.get(position).getDataList().getData().getCover().getFeed();
        Glide.with(mContext).load(feedUrl).into(holder.videoImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView videoImage;

        public ViewHolder(View itemView) {
            super(itemView);
            videoImage = (ImageView)itemView.findViewById(R.id.video_image);
        }
    }
}
