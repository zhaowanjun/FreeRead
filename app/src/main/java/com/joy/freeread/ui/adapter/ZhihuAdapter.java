package com.joy.freeread.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joy.freeread.R;
import com.joy.freeread.bean.zhihu.Story;
import com.joy.freeread.bean.zhihu.TopStories;
import com.joy.freeread.ui.view.LoadMoreListView;

import java.util.List;

/**
 * Created by zhaowanjun on 2017/11/24.
 */

public class ZhihuAdapter extends BaseAdapter {

    private Context mContext;
    private LoadMoreListView mLoadMoreListView;
    private List<Story> mData;

    public ZhihuAdapter(Context context, LoadMoreListView loadMoreListView) {
        mContext = context;
        mLoadMoreListView = loadMoreListView;
    }

    /**
     * 设置轮播图数据
     * @param top_stories 轮播图数据
     */
    public void setTopStories(List<TopStories> top_stories) {
        if(top_stories != null && top_stories.size()>0) {
            mLoadMoreListView.setHeadView(top_stories);
        }
    }

    /**
     * 给适配器设置数据
     * @param data 解析的javabean对象
     */
    public void setData(List<Story> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        if(mData != null && mData.size()>0) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public Story getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(getItem(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {

            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_zhihu_lv, null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_zhihu_item);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_zhihu_title);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Story story = mData.get(position);
        Glide.with(mContext).load(story.getImages()[0]).into(viewHolder.icon);
        viewHolder.title.setText(story.getTitle());
        return convertView;

    }

    static class ViewHolder {
        ImageView icon;
        TextView title;
    }

}
