package com.welab.league.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.factory.ViewFactory;
import com.welab.league.widget.BaseFragment;

import java.util.ArrayList;

import static com.welab.league.factory.ViewFactory.VIEW_TYPE_CATEGORY;

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseItemInfo> mCategoryDataList = new ArrayList<BaseItemInfo>();

    public CategoryPagerAdapter(android.support.v4.app.FragmentManager fm, ArrayList<BaseItemInfo> categoryDataList) {
        super(fm);

        mCategoryDataList = categoryDataList;
    }

    @Override
    public Fragment getItem(int position) {
        BaseItemInfo item = mCategoryDataList.get(position);

        BaseFragment baseFragment = ViewFactory.getInstance().getFragment(VIEW_TYPE_CATEGORY);
        baseFragment.setData(item);

        return baseFragment;
    }

    @Override
    public int getCount() {
        return mCategoryDataList.size();
    }
}
