package com.welab.league.adapter.holder;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.adapter.SortListViewAdapter;
import com.welab.league.api.weblab.response.LocalFilterItemInfo;
import com.welab.league.data.ReloadData;
import com.welab.league.listener.OnCallViewListener;
import com.welab.league.listener.OnReloadListener;
import com.welab.league.listener.OnResultListener;
import com.welab.league.util.Utils;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class LocalFilterViewHolder extends BaseViewHolder<LocalFilterItemInfo> {

    private LinearLayout mLocalContainer;
    private View mFilterView;
    private View mSearchView;
    private View mFilterItemDividerView;
    private TextView mTitleTextView;
    private PopupWindow mSortPopupWindow;
    private RecyclerView mSortListView;

    private OnReloadListener mListItemOnReloadListener;

    private ArrayList<String> mFilterList = null;
    final ArrayList<String> SELECTED_DATA_LIST = new ArrayList<>();

    private Context mContext;

    public LocalFilterViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.local_filter_layout, parent, false));

        mContext = context;
    }

    public LocalFilterViewHolder(View itemView) {
        super(itemView);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(itemView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Utils.getDrawable(itemView.getContext(), R.drawable.recyclerview_divider));

        mSortListView = (RecyclerView) LayoutInflater.from(itemView.getContext()).inflate(R.layout.sort_recyclerview_layout, null, false);
        mSortListView.addItemDecoration(dividerItemDecoration);
        mSortListView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false));

        mLocalContainer = (LinearLayout) itemView.findViewById(R.id.local_container);
        mTitleTextView = (TextView) itemView.findViewById(R.id.title_textview);
        mFilterView = itemView.findViewById(R.id.filter_view);
        mFilterItemDividerView = itemView.findViewById(R.id.divider_imageview);

        mFilterView.setOnClickListener(view -> {
            if (itemView.getContext() instanceof OnCallViewListener) {
                ((OnCallViewListener) itemView.getContext())
                        .onCallView(OnCallViewListener.VIEW_TYPE.LOCAL_NAME_VIEW, SELECTED_DATA_LIST,
                                new OnResultListener() {
                                    @Override
                                    public void onResult(List<String> dataList) {
                                        mLocalContainer.removeAllViews();

                                        if (SELECTED_DATA_LIST.size() > 0) {
                                            mTitleTextView.setVisibility(View.GONE);
                                            mLocalContainer.setVisibility(View.VISIBLE);

                                            for(int i = 0; i < SELECTED_DATA_LIST.size(); i++) {
                                                TextView localNameIconTextView = (TextView) LayoutInflater.from(itemView.getContext()).inflate(R.layout.filter_icon_textview, mLocalContainer, false);
                                                localNameIconTextView.setText(SELECTED_DATA_LIST.get(i));
                                                localNameIconTextView.setOnClickListener(view -> {
                                                    mLocalContainer.removeView(view);

                                                    SELECTED_DATA_LIST.remove(((TextView) view).getText().toString());
                                                });

                                                mLocalContainer.addView(localNameIconTextView);
                                            }

                                            mSearchView.setOnClickListener(view -> {
                                                mListItemOnReloadListener.onReload(new ReloadData(ReloadData.TYPE_SEARCH_KEYWORD_LIST, "", SELECTED_DATA_LIST));
                                            });

                                        } else {
                                            mTitleTextView.setVisibility(View.VISIBLE);
                                            mLocalContainer.setVisibility(View.GONE);

                                            mSearchView.setOnClickListener(null);
                                        }
                                    }
                                });
            }
        });

        mSearchView = itemView.findViewById(R.id.search_view);
    }

    public void setData(LocalFilterItemInfo localFilterItemInfo) {
        mListItemOnReloadListener = localFilterItemInfo.getReloadListener();

        mTitleTextView.setText(localFilterItemInfo.getTitle());

        mSortListView.setAdapter(new SortListViewAdapter(mContext, mListItemOnReloadListener, new SortListViewAdapter.OnClosePopupWindowListener() {
            @Override
            public void onClose() {
                mSortPopupWindow.dismiss();
            }
        }));

        if (localFilterItemInfo.getUseFilter() == false) {
            setFilterItemVisibility(View.GONE);
        } else {
            setFilterItemVisibility(View.VISIBLE);
        }

        mTitleTextView.setOnClickListener(view -> {
            mSortPopupWindow = new PopupWindow(mSortListView, mContext.getResources().getDimensionPixelSize(R.dimen.sort_popup_width), RelativeLayout.LayoutParams.WRAP_CONTENT);
            mSortPopupWindow.setFocusable(true);
            mSortPopupWindow.setTouchable(true);
            mSortPopupWindow.setOutsideTouchable(true);
            mSortPopupWindow.showAsDropDown(mTitleTextView, 0, 0);
        });
    }

    private void setFilterItemVisibility(int visibility) {
        mSearchView.setVisibility(visibility);
        mFilterView.setVisibility(visibility);
        mFilterItemDividerView.setVisibility(visibility);
    }
}
