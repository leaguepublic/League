package com.welab.league.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.api.weblab.response.BaseItemInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotiFragment extends BaseFragment<List<BaseItemInfo>> {


    public NotiFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setData(@NotNull List<List<BaseItemInfo>> mutableList) {
    }
}
