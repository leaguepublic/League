package com.welab.league.adapter.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.adapter.ListViewHolderAdapter;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.api.weblab.response.LocalFilterItemInfo;
import com.welab.league.factory.ViewHolderFactory;
import com.welab.league.listener.OnReloadListener;
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

        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mListViewHolderAdapter);
    }

    @Override
    public void setListData(List<BaseItemInfo> listDataList) {
        mListDataList.clear();

        if (listDataList.get(0).getType() == ViewHolderFactory.VIEW_TYPE_MATCH) {
            mListDataList.add(new LocalFilterItemInfo(new OnReloadListener() {
                @Override
                public void onReload(List<String> valueList) {
                    callApi(valueList);
                }
            }));
        }

        mListDataList.addAll(listDataList);

        mListViewHolderAdapter.notifyDataSetChanged();
    }

    private void callApi(List<String> valueList) {
        Log.e("TAG", "LJS== LocalFilterItemInfo API - value : " + valueList);
    }
}
