package com.welab.league.listener;

public interface OnCallViewListener {
    public enum VIEW_TYPE {
        LOCAL_NAME_MENU
    }

    public void onCallView(VIEW_TYPE viewType);
}
