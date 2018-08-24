package com.welab.league.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.welab.league.R;
import com.welab.league.listener.OnReloadListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SortListViewAdapter extends RecyclerView.Adapter {
    private final String SHARED_PREF_KEY = "current_index";

    private Context mContext;
    private SharedPreferences mSharedPref;

    private ArrayList<String> mDataList;

    private OnReloadListener mOnReloadListener;
    private OnClosePopupWindowListener mOnClosePopupWindowListener;

    public SortListViewAdapter(Context context, OnReloadListener onReloadListener, OnClosePopupWindowListener onClosePopupWindowListener) {
        mContext = context;
        mOnReloadListener = onReloadListener;
        mOnClosePopupWindowListener = onClosePopupWindowListener;

        mSharedPref = context.getSharedPreferences("sort_current_index", MODE_PRIVATE);

        setData(context);
    }

    // https://github.com/skydoves/PreferenceRoom/tree/master/preferenceroom-compiler/src/main/java/com/skydoves/processor - 이거 시간 날때 분석해 보자.. MVVM 처럼 자동으로 class를 만들어 준데.. 이거 어떻게 만드는지 궁금해..
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.check_image_right_checkbox, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).setData(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(String title, final int position) {
            CheckedTextView itemCheckedTextView = (CheckedTextView) itemView;

            itemCheckedTextView.setText(title);
            itemCheckedTextView.setOnClickListener(null);
            itemCheckedTextView.setChecked(mSharedPref.getInt(SHARED_PREF_KEY, 0) == position);
            itemCheckedTextView.setOnClickListener(view -> {
                mSharedPref.edit().putInt(SHARED_PREF_KEY, position).commit();

                notifyDataSetChanged();

                mOnClosePopupWindowListener.onClose();
            });
        }
    }

    private void setData(Context context) {
        mDataList = new ArrayList<>();
        mDataList.add(context.getString(R.string.sort_new_joinning_team));
        mDataList.add(context.getString(R.string.sort_high_victory));
        mDataList.add(context.getString(R.string.sort_good_manner));
        mDataList.add(context.getString(R.string.sort_high_select_count));
    }

    public interface OnClosePopupWindowListener {
        public void onClose();
    }
}
