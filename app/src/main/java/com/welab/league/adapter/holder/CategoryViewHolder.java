package com.welab.league.adapter.holder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewHolder extends BaseViewHolder<BaseItemInfo> {

    private ArrayList<BaseItemInfo> mListDataList = new ArrayList<>();

    public CategoryViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_viewholder_layout, parent, false));
    }

    public CategoryViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setListData(List<BaseItemInfo> listDataList) {
        mListDataList.clear();
        mListDataList.addAll(listDataList);
    }

    private void callApi(List<String> valueList) {
        Log.e("TAG", "LJS== LocalFilterItemInfo API - value : " + valueList);
    }
}
