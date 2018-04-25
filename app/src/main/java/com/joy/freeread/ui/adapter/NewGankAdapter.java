package com.joy.freeread.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.joy.freeread.bean.gank.Meizhi;

import java.util.List;

/**
 * Created by admin on 2018/4/25.
 */
public class NewGankAdapter extends BaseQuickAdapter<List<Meizhi.ResultsBean>, BaseViewHolder> {

    public NewGankAdapter(List<List<Meizhi.ResultsBean>> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, List<Meizhi.ResultsBean> item) {

    }
}
