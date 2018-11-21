package com.welab.league.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.api.weblab.response.RecentKeywordInfo;
import com.welab.league.db.DbUtils;
import com.welab.league.factory.ViewFactory;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SearchResultListAdapter extends RecyclerView.Adapter {

    private DbUtils mDbUtils;
    private ViewFactory mViewFactory;

    private List<BaseItemInfo> mDataList = new ArrayList<>();

    private Context mContext;

    public SearchResultListAdapter(Context context, List<BaseItemInfo> dataList) {
        mContext = context;

        mDataList = dataList;

        mDbUtils = DbUtils.getInstance(context);
        mViewFactory = ViewFactory.getInstance();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        viewHolder = mViewFactory.getViewHodler(mContext, parent, viewType);

        if (viewHolder == null) {
            viewHolder = new KeywordViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recent_keyword_list_item, parent, false));
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseViewHolder) {
            mViewFactory.setData(holder, mDataList.get(position));
        } else {
            ((KeywordViewHolder) holder).setData(mDataList.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setItemList(List<BaseItemInfo> dataList) {
        mDataList = dataList;
    }

    private class KeywordViewHolder extends RecyclerView.ViewHolder {

        private TextView mKeywordTextView;
        private View mDeleteButton;

        public KeywordViewHolder(View itemView) {
            super(itemView);

            mKeywordTextView = (TextView) itemView.findViewById(R.id.keyword_textview);
            mDeleteButton = itemView.findViewById(R.id.delete_imagebutton);
        }

        public void setData(final BaseItemInfo itemInfo, final int postion) {
            String keyword = ((RecentKeywordInfo) itemInfo).getKeyword();

            mKeywordTextView.setText(keyword);
            mDeleteButton.setOnClickListener(view -> {
                if (mDbUtils.deleteKeyword(keyword) > 0) {
                    mDataList.remove(itemInfo);
                    notifyItemRangeRemoved(postion, 1);
                }
            });
        }
    }
}
