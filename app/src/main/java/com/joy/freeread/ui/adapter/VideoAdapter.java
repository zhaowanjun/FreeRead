package com.joy.freeread.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joy.freeread.R;
import com.joy.freeread.bean.video.DataBean;
import com.joy.freeread.bean.video.ItemListBean;
import com.joy.freeread.utils.ScreenUtil;

import java.util.List;

import butterknife.Bind;

/**
 * Created by admin on 2018/3/30.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> implements View.OnClickListener, View.OnTouchListener {

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
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.video_item, parent, false);
        //宽高比例16:9
        inflate.getLayoutParams().height = ScreenUtil.instance(mContext).getScreenWidth()*9/16;
        ViewHolder viewHolder = new ViewHolder(inflate);
        inflate.setOnClickListener(this);
        inflate.setOnTouchListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //将数据填充到具体的view中
        if (mData != null && mData.size() > 0) {

            DataBean data = mData.get(position).getData();
            String feedUrl = data.getCover().getFeed();
            //视频封面
            Glide.with(mContext).load(feedUrl).centerCrop().into(holder.mVideoImage);
            //视频标题
            String title = data.getTitle();
            holder.mVideoTitle.setText(title);
            //视频分类
            String catrgory = data.getCategory();
            holder.mVideoCategory.setText(catrgory);
            //视频时长
            int duration = data.getDuration();
            holder.mVideoDuration.setText(duration/60 + "′ " + duration%60 +"″");

            //设置tag为点击事件传递数据
            holder.itemView.setTag(data);
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mVideoImage;
        public TextView mVideoTitle;
        public TextView mVideoCategory;
        public TextView mVideoDuration;

        public ViewHolder(View itemView) {
            super(itemView);
            mVideoImage = (ImageView) itemView.findViewById(R.id.video_image);
            mVideoTitle = (TextView) itemView.findViewById(R.id.video_title);
            mVideoCategory = (TextView) itemView.findViewById(R.id.video_category);
            mVideoDuration = (TextView) itemView.findViewById(R.id.video_duration);
        }
    }

    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(DataBean data);
    }

    @Override
    public void onClick(View view) {
        if(mItemClickListener != null) {
            mItemClickListener.onItemClick((DataBean) view.getTag());
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ((ViewGroup)view).getChildAt(1).setVisibility(View.GONE);
                break;
            case MotionEvent.ACTION_MOVE:
                ((ViewGroup)view).getChildAt(1).setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_UP:
                ((ViewGroup)view).getChildAt(1).setVisibility(View.VISIBLE);
                break;
        }
        return false;
    }
}
