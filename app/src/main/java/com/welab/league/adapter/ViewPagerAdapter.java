package com.welab.league.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.factory.ViewFactory;
import com.welab.league.widget.BaseFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ViewFactory mViewFactory;

    private ArrayList<BaseItemInfo> mItemInfoList;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<BaseItemInfo> itemInfoList) {
        super(fm);

        mItemInfoList = itemInfoList;

        mViewFactory = ViewFactory.getInstance();
    }

    @Override
    public Fragment getItem(int position) {
        BaseItemInfo item = mItemInfoList.get(position);

        BaseFragment baseFragment = mViewFactory.getFragment(item.getType());
        baseFragment.setData(item);

        return baseFragment;
    }

    @Override
    public int getCount() {
        if (mItemInfoList == null) {
            return 0;
        } else {
            return mItemInfoList.size();
        }
    }
}
