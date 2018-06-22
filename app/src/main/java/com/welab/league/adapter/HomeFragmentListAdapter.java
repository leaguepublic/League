package com.welab.league.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.welab.league.api.response.weblab.BaseItemInfo;
import com.welab.league.factory.ViewHolderFactory;

import java.util.ArrayList;

public class HomeFragmentListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ViewHolderFactory mViewHolderFactory;

    private ArrayList<BaseItemInfo> mItemInfoList;

    // http://trend21c.tistory.com/2021 - Viewpager를 여러개 사용할 경우 setId를 해줘야 함.

    public HomeFragmentListAdapter(Context context, ArrayList<BaseItemInfo> iItemInfoList) {
        mContext = context;

        mItemInfoList = iItemInfoList;

        mViewHolderFactory = ViewHolderFactory.getInstance();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemInfoList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mViewHolderFactory.get(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mItemInfoList.size();
    }
}