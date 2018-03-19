package com.joy.freeread.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.joy.freeread.R;
import com.joy.freeread.bean.zhihu.TopStories;

import java.util.List;

/**
 * Created by zhaowanjun on 2017/11/24.
 */

public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    private View mFootView;//脚布局
    private int mTotalItemCount;//item总数
    private boolean isLoading = false;
    private OnLoadMoreListener onLoadMoreListener;
    private OnHeadItemClickListener onHeadItemClickListener;
    private FrameLayout mHeadView;
    private SliderLayout mSliderLayout;

    public LoadMoreListView(Context context) {
        this(context, null);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHeadView = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_zhihu_head, null);
//        addHeaderView(mHeadView);

        mFootView = LayoutInflater.from(getContext()).inflate(R.layout.item_zhihu_foot, null);
        setOnScrollListener(this);
    }

    /**
     * 给轮播图设置数据
     * @param data 轮播图数据
     */
    public void setHeadView(List<TopStories> data) {
        if(getHeaderViewsCount() == 0) {
            addHeaderView(mHeadView);
        }
        mSliderLayout = (SliderLayout) mHeadView.getChildAt(0);
        mSliderLayout.removeAllSliders();
        for (int i = 0; i < data.size(); i++) {
            final TopStories topStory = data.get(i);
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView.description(topStory.getTitle());
            textSliderView.image(topStory.getImage());
            textSliderView.setScaleType(BaseSliderView.ScaleType.CenterCrop);
            final int position = i;
            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    //点击轮播图
                    onHeadItemClickListener.headItemClick(topStory, position);
                }
            });

            mSliderLayout.addSlider(textSliderView);
        }

        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Tablet);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mSliderLayout.setDuration(5000);
    }

    @Override
    public void onScrollStateChanged(AbsListView listView, int scrollState) {
        int lastVisibleIndex = listView.getLastVisiblePosition();
        if (!isLoading
                && scrollState == OnScrollListener.SCROLL_STATE_IDLE
                && lastVisibleIndex == mTotalItemCount - 1) {
            addFooterView(mFootView);
            isLoading = true;
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        mTotalItemCount = totalItemCount;
    }

    public void setLoadCompleted() {
        isLoading = false;
        removeFooterView(mFootView);
    }


    /**
     * 监听加载更多
     * @param onLoadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }
    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    /**
     * 监听轮播图被点击
     * @param onHeadItemClickListener
     */
    public void setOnHeadItemClickListener(OnHeadItemClickListener onHeadItemClickListener) {
        this.onHeadItemClickListener = onHeadItemClickListener;
    }
    public interface OnHeadItemClickListener {
        void headItemClick(TopStories data, int position);
    }
}
