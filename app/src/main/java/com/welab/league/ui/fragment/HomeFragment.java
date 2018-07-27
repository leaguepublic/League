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
import com.welab.league.api.ApiManager;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.api.weblab.response.ResTabHome;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView mHomeListView;

    private HomeFragmentListAdapter mHomeFragmentListAdapter;

    private ArrayList<List<BaseItemInfo>> mHomeItemInfoList = new ArrayList<List<BaseItemInfo>>();

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mHomeFragmentListAdapter = new HomeFragmentListAdapter(getContext(), mHomeItemInfoList);

        View rootView = inflater.inflate(R.layout.recyclerview_layout, container, false);
        mHomeListView = (RecyclerView) rootView.findViewById(R.id.item_listview);
        mHomeListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mHomeListView.setAdapter(mHomeFragmentListAdapter);

        ApiManager apiManager = ApiManager.getInstance();

        apiManager.getTabHome("111", new Callback<ResTabHome>() {
            @Override
            public void onResponse(Call<ResTabHome> call, Response<ResTabHome> response) {
                ResTabHome resTabHome = response.body();

                if (resTabHome != null) {
                    for(List<BaseItemInfo> element : resTabHome.getResponse().getList()) {
                        if (element != null) {
                            mHomeItemInfoList.add(element);
                        }
                    }

                    mHomeFragmentListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResTabHome> call, Throwable t) {
                Log.e("TAG", "LJS== failure : " + t.getMessage());
            }
        });

        return mHomeListView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
