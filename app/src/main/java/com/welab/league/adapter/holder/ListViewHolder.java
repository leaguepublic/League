package com.welab.league.adapter.holder;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.adapter.ListViewHolderAdapter;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.api.weblab.response.LocalFilterItemInfo;
import com.welab.league.data.ReloadData;
import com.welab.league.factory.ViewFactory;
import com.welab.league.listener.OnReloadListener;
import com.welab.league.util.Utils;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListViewHolder extends BaseViewHolder<BaseItemInfo> {

    private RecyclerView mRecyclerView;

    private ListViewHolderAdapter mListViewHolderAdapter;

    private ArrayList<BaseItemInfo> mListDataList = new ArrayList<>();

    public ListViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false));
    }

    public ListViewHolder(View itemView) {
        super(itemView);

        // ListView 형태
        mListViewHolderAdapter = new ListViewHolderAdapter(itemView.getContext(), mListDataList);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(itemView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Utils.getDrawable(itemView.getContext(), R.drawable.recyclerview_divider));

        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_listview);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mListViewHolderAdapter);
    }

    @Override
    public void setListData(List<BaseItemInfo> listDataList) {
        mListDataList.clear();

        if (listDataList.get(0).getType() == ViewFactory.VIEW_TYPE_LOCAL_FILTER) {
            ((LocalFilterItemInfo) listDataList.get(0)).setReloadListener(new OnReloadListener() {
                @Override
                public void onReload(ReloadData reloadData) {
                    callApi(reloadData);
                }
            });
        }

        mListDataList.addAll(listDataList);

        mListViewHolderAdapter.notifyDataSetChanged();
    }

    private void callApi(ReloadData reloadData) {
        // mListDataList 여기에 넣으면 됨. 그리고 어댑터 다시 호출

        switch (reloadData.getType()) {
            case ReloadData.TYPE_SORT:
                Log.e("TAG", "LJS== Call Api by Sort == " + reloadData.getSortType());
                break;

            case ReloadData.TYPE_SEARCH_KEYWORD_LIST:
                Log.e("TAG", "LJS== Call Api by search keyword(localname) == " + reloadData.getSearchKeywordList());
                break;

        }
    }
}
