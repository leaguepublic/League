package com.welab.league.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.adapter.HomeFragmentListAdapter;
import com.welab.league.api.response.weblab.BaseItemInfo;
import com.welab.league.api.response.weblab.MatchTeamInfo;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView mHomeListView;

    private HomeFragmentListAdapter mHomeFragmentListAdapter;

    private ArrayList<BaseItemInfo> mHomeItemInfoList = new ArrayList<>();

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("TAG", "LJS== HomeFragment - onCreateView()");

        mHomeItemInfoList.add(new MatchTeamInfo());

        mHomeFragmentListAdapter = new HomeFragmentListAdapter(getContext(), mHomeItemInfoList);

        View rootView = inflater.inflate(R.layout.recyclerview_layout, container, false);
        mHomeListView = (RecyclerView) rootView.findViewById(R.id.item_listview);
        mHomeListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mHomeListView.setAdapter(mHomeFragmentListAdapter);

        return mHomeListView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
