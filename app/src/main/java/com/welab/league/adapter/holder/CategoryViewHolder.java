package com.welab.league.adapter.holder;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.adapter.CategoryPagerAdapter;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewHolder extends BaseViewHolder<BaseItemInfo> {

    private ViewPager mViewPager;
    private CategoryPagerAdapter mCategoryPagerAdapter;

    private ArrayList<BaseItemInfo> mListDataList = new ArrayList<>();

    public CategoryViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_viewholder_layout, parent, false));
    }

    public CategoryViewHolder(View itemView) {
        super(itemView);

        TabLayout tabLayout = (TabLayout) itemView.findViewById(R.id.tabs);

        mCategoryPagerAdapter = new CategoryPagerAdapter(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), mListDataList);

        mViewPager = (ViewPager) itemView.findViewById(R.id.category_viewpager);
        mViewPager.setOffscreenPageLimit(mListDataList.size());
        mViewPager.setAdapter(mCategoryPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void setListData(List<BaseItemInfo> listDataList) {
        mListDataList.clear();
        mListDataList.addAll(listDataList);

        if (mCategoryPagerAdapter != null) {
            mCategoryPagerAdapter.notifyDataSetChanged();
        }
    }
}
