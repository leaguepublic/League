package com.welab.league.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.widget.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RisingTeamFragment extends BaseFragment<BaseItemInfo> {


    public RisingTeamFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.rising_team_item, container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setData(@NotNull List<BaseItemInfo> mutableList) {
        Log.e("TAG", "LJS== RisingTeam mutableList : " + mutableList);
    }
}
