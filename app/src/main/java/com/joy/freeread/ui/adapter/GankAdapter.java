package com.joy.freeread.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joy.freeread.R;
import com.joy.freeread.bean.gank.Meizhi;

import java.util.List;

/**
 * Created by admin on 2018/4/23.
 */
public class GankAdapter extends RecyclerView.Adapter<GankAdapter.ViewHolder> {

    private Context mContext;
    private List<Meizhi.ResultsBean> mMeizhiList;

    public GankAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.gank_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //将数据填充到布局文件

        //图片
        Meizhi.ResultsBean resultsBean = mMeizhiList.get(position);
        String url = resultsBean.getUrl();
        Glide.with(mContext).load(url).into(holder.mCardImage);

        //文字
        String publishedAt = resultsBean.getPublishedAt();
        String date = publishedAt.split("T")[0];
        holder.mCardText.setText(date);
    }

    @Override
    public int getItemCount() {
        if(mMeizhiList != null && mMeizhiList.size() > 0) {
            return mMeizhiList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mCardImage;
        private final TextView mCardText;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardImage = (ImageView) itemView.findViewById(R.id.card_image);
            mCardText = (TextView) itemView.findViewById(R.id.card_text);
        }
    }

    public void setData(List<Meizhi.ResultsBean> meizhiList) {
        mMeizhiList = meizhiList;
    }
}
