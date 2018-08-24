package com.welab.league.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.welab.league.factory.ViewFactory;

public class MainTabFragmentPagerAdapter extends FragmentPagerAdapter {

    private int mTabDataCount;

    public MainTabFragmentPagerAdapter(android.support.v4.app.FragmentManager fm, int tabdataCount) {
        super(fm);

        mTabDataCount = tabdataCount;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewFactory.getInstance().getFragment(position);
    }

    @Override
    public int getCount() {
        return mTabDataCount;
    }
}
