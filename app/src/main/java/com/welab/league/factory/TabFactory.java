package com.welab.league.factory;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.ui.fragment.HomeFragment;
import com.welab.league.ui.fragment.MatchFragment;
import com.welab.league.ui.fragment.NotiFragment;
import com.welab.league.ui.fragment.TeamFragment;
import com.welab.league.ui.fragment.VictoryFragment;

import java.util.ArrayList;

public class TabFactory {
    public static final int TAB_INDEX_HOME = 0;
    public static final int TAB_INDEX_MATCH = 1;
    public static final int TAB_INDEX_TEAM = 2;
    public static final int TAB_INDEX_VICTORY = 3;
    public static final int TAB_INDEX_NOTI = 4;

    private int mTabCount;

    private ArrayList<BaseItemInfo> mPageDataList;

    private static class Singleton {
        private static final TabFactory INSTANCE = new TabFactory();
    }

    private TabFactory() {}

    public static TabFactory getInstance () {
        return Singleton.INSTANCE;
    }

    public void setData(ArrayList<BaseItemInfo> pageDataList) {
        mPageDataList = pageDataList;
    }

    public Fragment getFragment(int tabIndex) {
        Fragment fragment = null;

        switch (tabIndex) {
            case TAB_INDEX_HOME:
                fragment = new HomeFragment();
                break;

            case TAB_INDEX_MATCH:
                fragment = new MatchFragment();
                break;

            case TAB_INDEX_TEAM:
                fragment = new TeamFragment();
                break;

            case TAB_INDEX_VICTORY:
                fragment = new VictoryFragment();
                break;

            case TAB_INDEX_NOTI:
                fragment = new NotiFragment();
                break;
        }

        Log.e("TAG", "LJs== getFragment : " + fragment);

        return fragment;
    }

    public int getTabCount() {
        return mTabCount;
    }

    public void setTab(Activity activity, TabLayout tabLayout) {
        setTabTitle(activity, tabLayout, R.string.tab_home, TAB_INDEX_HOME);
        setTabTitle(activity, tabLayout, R.string.tab_match, TAB_INDEX_MATCH);
        setTabTitle(activity, tabLayout, R.string.tab_team, TAB_INDEX_TEAM);
        setTabTitle(activity, tabLayout, R.string.tab_victory, TAB_INDEX_VICTORY);
        setTabTitle(activity, tabLayout, R.string.tab_noti, TAB_INDEX_NOTI);

        mTabCount = tabLayout.getTabCount();
    }

    private void setTabTitle(Activity activity, TabLayout tabLayout, int title, int position) {
        TextView tabTitleTextView = (TextView) activity.getLayoutInflater().inflate(R.layout.tab_textview, null);
        tabTitleTextView.setText(title);

        tabLayout.addTab(tabLayout.newTab().setCustomView(tabTitleTextView), position);
    }
}
