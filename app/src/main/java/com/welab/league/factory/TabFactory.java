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
