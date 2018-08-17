package com.welab.league.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.api.weblab.response.MoreInfo;
import com.welab.league.factory.Navigator;
import com.welab.league.widget.BaseViewHolder;

public class MoreViewHolder extends BaseViewHolder<MoreInfo> {

    private View mRootView;

    private Context mContext;

    public MoreViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.more_item, parent, false));

        mContext = context;
    }

    public MoreViewHolder(View itemView) {
        super(itemView);

        mRootView = itemView.findViewById(R.id.root_view);
    }

    @Override
    public void setData(final MoreInfo moreInfo) {
        mRootView.setOnClickListener(view -> {
            Navigator.callActivity(mContext, moreInfo.getCallType());
        });
    }
}
