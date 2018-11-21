package com.welab.league.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.adapter.HomeFragmentListAdapter;
import com.welab.league.api.ApiManager;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.util.Utils;
import com.welab.league.widget.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonListFragment extends BaseFragment {

    private RecyclerView mHomeListView;

    protected HomeFragmentListAdapter mHomeFragmentListAdapter;

    protected List<List<BaseItemInfo>> mListItemInfoList = new ArrayList<List<BaseItemInfo>>();

    public CommonListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHomeFragmentListAdapter = new HomeFragmentListAdapter(getContext(), mListItemInfoList);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Utils.getDrawable(getContext(), R.drawable.recyclerview_main_divider));

        View rootView = inflater.inflate(R.layout.recyclerview_layout, container, false);

        mHomeListView = (RecyclerView) rootView.findViewById(R.id.item_listview);
        mHomeListView.addItemDecoration(dividerItemDecoration);
        mHomeListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mHomeListView.setAdapter(mHomeFragmentListAdapter);

        callApi(ApiManager.getInstance());

        return mHomeListView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    protected abstract void callApi(ApiManager apiManager);
}
