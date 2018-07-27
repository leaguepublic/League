package com.welab.league.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.welab.league.R;
import com.welab.league.api.weblab.response.LocalFilterItemInfo;
import com.welab.league.listener.OnCallViewListener;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;

public class LocalFilterViewHolder extends BaseViewHolder<LocalFilterItemInfo> {

    private LinearLayout mLocalContainer;
    private View mSearchView;

    private ArrayList<String> mFilterList = null;

    public LocalFilterViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.local_filter_layout, parent, false));
    }

    public LocalFilterViewHolder(View itemView) {
        super(itemView);

        mLocalContainer = (LinearLayout) itemView.findViewById(R.id.local_container);
        itemView.findViewById(R.id.filter_view).setOnClickListener(view -> {
            if (itemView.getContext() instanceof OnCallViewListener) {
                ((OnCallViewListener) itemView.getContext()).onCallView(OnCallViewListener.VIEW_TYPE.LOCAL_NAME_MENU);
            }
        });

        mSearchView = itemView.findViewById(R.id.search_view);
    }

    public void setData(LocalFilterItemInfo localFilterItemInfo) {
        mSearchView.setOnClickListener(view -> {
            localFilterItemInfo.getReloadListener().onReload(null);
        });
    }
}
