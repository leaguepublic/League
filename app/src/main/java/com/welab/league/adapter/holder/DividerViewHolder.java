package com.welab.league.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.widget.BaseViewHolder;

public class DividerViewHolder extends BaseViewHolder<BaseItemInfo> {

    public DividerViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.divider_item, parent, false));
    }

    public DividerViewHolder(View itemView) {
        super(itemView);
    }

}
