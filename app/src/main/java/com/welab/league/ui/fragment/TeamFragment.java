package com.welab.league.ui.fragment;


import android.util.Log;

import com.welab.league.api.ApiManager;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.api.weblab.response.DividerInfo;
import com.welab.league.api.weblab.response.ResTabHome;
import com.welab.league.factory.DataConverter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamFragment extends CommonListFragment {

    public TeamFragment() {
    }

    @Override
    protected void callApi(ApiManager apiManager) {
        apiManager.getTabHome("111", new Callback<ResTabHome>() {
            @Override
            public void onResponse(Call<ResTabHome> call, Response<ResTabHome> response) {
                ResTabHome resTabHome = response.body();

                if (resTabHome != null) {

                    ArrayList<BaseItemInfo> dividerInfoList = new ArrayList<>();
                    dividerInfoList.add(new DividerInfo());

                    mListItemInfoList.add(dividerInfoList);
                    for(List<BaseItemInfo> element : resTabHome.getResponse().getList()) {
                        if (element != null) {
                            mListItemInfoList.add(DataConverter.convert(getContext(), element));
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
    }
}
