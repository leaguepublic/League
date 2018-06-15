package com.welab.league.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.db.DbUtils;

import java.util.ArrayList;
import java.util.List;

public class RecentKeywordListAdapter extends RecyclerView.Adapter {

    private DbUtils mDbUtils;

    private List<String> mKeywordList = new ArrayList<>();

    private Context mContext;

    public RecentKeywordListAdapter(Context context, List<String> keywordList) {
        mContext = context;

        mKeywordList = keywordList;

        mDbUtils = DbUtils.getInstance(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KeywordViewHolder(View.inflate(mContext, R.layout.recent_keyword_list_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((KeywordViewHolder) holder).setData(mKeywordList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mKeywordList.size();
    }

    private class KeywordViewHolder extends RecyclerView.ViewHolder {

        private TextView mKeywordTextView;
        private View mDeleteButton;

        public KeywordViewHolder(View itemView) {
            super(itemView);

            mKeywordTextView = (TextView) itemView.findViewById(R.id.keyword_textview);
            mDeleteButton = itemView.findViewById(R.id.delete_imagebutton);
        }

        public void setData(final String keyword, final int postion) {
            mKeywordTextView.setText(keyword);
            mDeleteButton.setOnClickListener(view -> {
                if (mDbUtils.deleteKeyword(keyword) > 0) {
                    mKeywordList.remove(keyword);
                    notifyItemRangeRemoved(postion, 1);
                }
            });
        }
    }
}
