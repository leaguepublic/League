package com.welab.league.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.welab.league.data.TabInfo;
import com.welab.league.factory.FragmentFactory;

import java.util.ArrayList;

public class MainTabFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<TabInfo> mTabInfoList = new ArrayList<TabInfo>();

    public MainTabFragmentPagerAdapter(android.support.v4.app.FragmentManager fm, ArrayList<TabInfo> tabInfoList) {
        super(fm);

        mTabInfoList = tabInfoList;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getInstance().getMainTabFragment(position);
    }

    @Override
    public int getCount() {
        return mTabInfoList.size();
    }
}
