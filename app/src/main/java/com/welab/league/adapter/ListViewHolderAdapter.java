package com.welab.league.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.factory.ViewHolderFactory;

import java.util.ArrayList;

public class ListViewHolderAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ViewHolderFactory mViewHolderFactory;

    private ArrayList<BaseItemInfo> mListDataList;

    // http://trend21c.tistory.com/2021 - Viewpager를 여러개 사용할 경우 setId를 해줘야 함.

    public ListViewHolderAdapter(Context context, ArrayList<BaseItemInfo> matchInfoList) {
        mContext = context;

        mListDataList = matchInfoList;

        mViewHolderFactory = ViewHolderFactory.getInstance();
    }

    @Override
    public int getItemViewType(int position) {
        return mListDataList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mViewHolderFactory.get(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mViewHolderFactory.setData(holder, mListDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mListDataList.size();
    }
}
