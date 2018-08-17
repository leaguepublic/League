package com.welab.league.adapter.holder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.api.weblab.response.LocalFilterItemInfo;
import com.welab.league.listener.OnCallViewListener;
import com.welab.league.listener.OnResultListener;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class LocalFilterViewHolder extends BaseViewHolder<LocalFilterItemInfo> {

    private LinearLayout mLocalContainer;
    private View mSearchView;
    private TextView mTitleTextView;

    private ArrayList<String> mFilterList = null;

    public LocalFilterViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.local_filter_layout, parent, false));
    }

    public LocalFilterViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title_textview);
        mLocalContainer = (LinearLayout) itemView.findViewById(R.id.local_container);

        itemView.findViewById(R.id.filter_view).setOnClickListener(view -> {
            if (itemView.getContext() instanceof OnCallViewListener) {
                ((OnCallViewListener) itemView.getContext())
                        .onCallView(OnCallViewListener.VIEW_TYPE.LOCAL_NAME_MENU,
                                new OnResultListener() {
                                    @Override
                                    public void onResult(List<String> dataList) {
                                        Log.e("TAG", "LJS== dataList : " + dataList);
                                        mLocalContainer.removeAllViews();

                                        for(String localNanme : dataList) {
                                            TextView localNameIconTextView = (TextView) LayoutInflater.from(itemView.getContext()).inflate(R.layout.filter_icon_textview, null, false);
                                            localNameIconTextView.setText(localNanme);
                                            mLocalContainer.addView(localNameIconTextView);
                                        }
                                    }
                                });
            }
        });

        mSearchView = itemView.findViewById(R.id.search_view);
    }

    public void setData(LocalFilterItemInfo localFilterItemInfo) {
        mSearchView.setOnClickListener(view -> {
            localFilterItemInfo.getReloadListener().onReload(null);
        });

        mTitleTextView.setText(localFilterItemInfo.getTitle());
    }
}
