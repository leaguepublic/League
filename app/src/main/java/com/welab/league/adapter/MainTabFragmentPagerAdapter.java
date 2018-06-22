package com.welab.league.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.welab.league.factory.TabFactory;

public class MainTabFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private TabFactory mTabFactory;


    public MainTabFragmentPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);

        mTabFactory = TabFactory.getInstance();
    }

    @Override
    public Fragment getItem(int position) {
        return mTabFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return mTabFactory.getTabCount();
    }
}
