package com.welab.league.listener;

import java.util.ArrayList;

public interface OnCallViewListener {
    public enum VIEW_TYPE {
        LOCAL_NAME_VIEW
    }

    public void onCallView(VIEW_TYPE viewType, ArrayList<String> selectedDataList, OnResultListener onResultListener);
}
