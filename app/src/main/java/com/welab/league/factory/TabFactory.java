package com.welab.league.factory;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.data.TabInfo;

import java.util.List;

public class TabFactory {
    public static final int TAB_INDEX_HOME = 0;
    public static final int TAB_INDEX_MATCH = 1;
    public static final int TAB_INDEX_TEAM = 2;
    public static final int TAB_INDEX_MATCH_RESULT_BALLOT = 3;
    public static final int TAB_INDEX_NOTI = 4;

//    public void setTab(Activity activity, TabLayout tabLayout) {
//        setTabTitle(activity, tabLayout, R.string.tab_home, TAB_INDEX_HOME);
//        setTabTitle(activity, tabLayout, R.string.tab_match, TAB_INDEX_MATCH);
//        setTabTitle(activity, tabLayout, R.string.tab_team, TAB_INDEX_TEAM);
//        setTabTitle(activity, tabLayout, R.string.tab_victory, TAB_INDEX_MATCH_RESULT_BALLOT);
//        setTabTitle(activity, tabLayout, R.string.tab_noti, TAB_INDEX_NOTI);
//
//        mTabCount = tabLayout.getTabCount();
//    }

    public void setTab(Activity activity, TabLayout tabLayout, List<TabInfo> tabInfoList) {
        for (TabInfo tabInfo : tabInfoList) {
            if (tabInfo.getTitleResId() > -1) {
                setTabTitle(activity, tabLayout, tabInfo.getTitleResId(), tabInfo.getTabIndex());
            } else if (TextUtils.isEmpty(tabInfo.getImageUrl()) == false) {
                setTabTitle(activity, tabLayout, tabInfo.getImageUrl(), tabInfo.getTabIndex());
            }
        }
    }

    private void setTabTitle(Activity activity, TabLayout tabLayout, int title, int position) {
        TextView tabTitleTextView = (TextView) activity.getLayoutInflater().inflate(R.layout.tab_textview, null);
        tabTitleTextView.setText(title);

        tabLayout.addTab(tabLayout.newTab().setCustomView(tabTitleTextView), position);
    }

    private void setTabTitle(Activity activity, TabLayout tabLayout, String imageUrl, int position) {
        ImageView tabImageView = (ImageView) activity.getLayoutInflater().inflate(R.layout.tab_imageview, null);

        tabLayout.addTab(tabLayout.newTab().setCustomView(tabImageView), position);
    }

}
