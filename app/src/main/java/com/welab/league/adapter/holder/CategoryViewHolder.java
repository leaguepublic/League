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
import com.welab.league.api.weblab.response.CategoryInfo;
import com.welab.league.data.TabData;
import com.welab.league.factory.TabFactory;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewHolder extends BaseViewHolder<CategoryInfo> {

    private ViewPager mViewPager;
    private CategoryPagerAdapter mCategoryPagerAdapter;

    private ArrayList<CategoryInfo> mListDataList = new ArrayList<>();

    public CategoryViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_viewholder_layout, parent, false));
    }

    public CategoryViewHolder(View itemView) {
        super(itemView);

        TabLayout tabLayout = (TabLayout) itemView.findViewById(R.id.tabs);

        ArrayList<TabData> tabDataList = new ArrayList<>();
        for (int i = 0; i < mListDataList.size(); i++) {
            tabDataList.add(new TabData(-1, mListDataList.get(i).getCategoryImageUrl(), i));
        }

        TabFactory tabFactory = new TabFactory();
        tabFactory.setTab(itemView.getContext(), tabLayout, tabDataList);

        mCategoryPagerAdapter = new CategoryPagerAdapter(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), mListDataList);

        mViewPager = (ViewPager) itemView.findViewById(R.id.category_viewpager);
        mViewPager.setOffscreenPageLimit(mListDataList.size());
        mViewPager.setAdapter(mCategoryPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void setListData(List<CategoryInfo> listDataList) {
        mListDataList.clear();
        mListDataList.addAll(listDataList);

        if (mCategoryPagerAdapter != null) {
            mCategoryPagerAdapter.notifyDataSetChanged();
        }
    }
}
