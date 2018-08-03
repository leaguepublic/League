package com.welab.league.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerViewHolder extends BaseViewHolder<BaseItemInfo> {

    private ArrayList<BaseItemInfo> mListDataList = new ArrayList<>();

    public ViewPagerViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_viewholder_layout, parent, false));
    }

    public ViewPagerViewHolder(View itemView) {
        super(itemView);

    }

    @Override
    public void setListData(List<BaseItemInfo> listDataList) {
        mListDataList.clear();
        mListDataList.addAll(listDataList);
    }
}
