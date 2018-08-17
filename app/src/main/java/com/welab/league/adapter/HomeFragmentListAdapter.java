package com.welab.league.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.factory.ViewFactory;

import java.util.List;

public class HomeFragmentListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ViewFactory mViewFactory;

    private List<List<BaseItemInfo>> mItemInfoList;

    // http://trend21c.tistory.com/2021 - Viewpager를 여러개 사용할 경우 setId를 해줘야 함.

    public HomeFragmentListAdapter(Context context, List<List<BaseItemInfo>> iItemInfoList) {
        mContext = context;

        mViewFactory = ViewFactory.getInstance();

        mItemInfoList = iItemInfoList;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewFactory.getListType(mItemInfoList.get(position));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mViewFactory.getViewHodler(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mViewFactory.setListData(holder, mItemInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItemInfoList == null) {
            return 0;
        } else {
            return mItemInfoList.size();
        }
    }
}
