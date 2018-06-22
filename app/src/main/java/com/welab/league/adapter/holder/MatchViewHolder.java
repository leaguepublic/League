package com.welab.league.adapter.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.adapter.MatchListAdapter;
import com.welab.league.api.response.weblab.BaseItemInfo;
import com.welab.league.api.response.weblab.LocalFilterItemInfo;
import com.welab.league.listener.OnReloadListener;

import java.util.ArrayList;
import java.util.List;

public class MatchViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView mRecyclerView;

    private MatchListAdapter mMatchListAdapter;

    private ArrayList<BaseItemInfo> mMatchInfoList = new ArrayList<>();

    public MatchViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false));
    }

    public MatchViewHolder(View itemView) {
        super(itemView);

        mMatchInfoList.add(new LocalFilterItemInfo(new OnReloadListener() {
            @Override
            public void onReload(List<String> valueList) {
                callApi(valueList);
            }
        }));

        mMatchListAdapter = new MatchListAdapter(itemView.getContext(), mMatchInfoList);

        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mMatchListAdapter);

        callApi(null);
    }

    private void callApi(List<String> valueList) {
        Log.e("TAG", "LJS== Call Match API - value : " + valueList);
    }
}
