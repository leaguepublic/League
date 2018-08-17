package com.welab.league.adapter.holder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.adapter.ViewPagerAdapter;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.api.weblab.response.ViewPagerItemInfo;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerViewHolder extends BaseViewHolder<ViewPagerItemInfo> {

    private TextView mTitleTextView;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private ArrayList<BaseItemInfo> mListDataList = new ArrayList<>();

    public ViewPagerViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_viewholder_layout, parent, false));
    }

    public ViewPagerViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title_textview);

        mViewPagerAdapter = new ViewPagerAdapter(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), mListDataList);

        mViewPager = (ViewPager) itemView.findViewById(R.id.body_viewpager);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public void setListData(List<ViewPagerItemInfo> listDataList) {
        mListDataList.clear();
        mListDataList.addAll(listDataList);

        if (mViewPagerAdapter != null) {
            mViewPagerAdapter.notifyDataSetChanged();
        }

        mTitleTextView.setText(listDataList.get(0).getTitle());
    }
}
