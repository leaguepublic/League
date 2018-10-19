package com.welab.league.factory;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.data.TabData;

import java.util.List;

public class TabFactory {
    public void setTab(Context context, TabLayout tabLayout, List<TabData> tabDataList) {
        for (TabData tabData : tabDataList) {
            if (tabData.getTitleResId() > -1) {
                setTabTitle(context, tabLayout, tabData.getTitleResId(), tabData.getTabIndex());
            } else if (TextUtils.isEmpty(tabData.getImageUrl()) == false) {
                setTabTitle(context, tabLayout, tabData.getImageUrl(), tabData.getTabIndex());
            }
        }
    }

    private void setTabTitle(Context context, TabLayout tabLayout, int title, int position) {
        TextView tabTitleTextView = (TextView) LayoutInflater.from(context).inflate(R.layout.tab_textview, null, false);
        tabTitleTextView.setText(title);

        tabLayout.addTab(tabLayout.newTab().setCustomView(tabTitleTextView), position);
    }

    private void setTabTitle(Context context, TabLayout tabLayout, String imageUrl, int position) {
        ImageView tabImageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.tab_imageview, null, false);

        tabLayout.addTab(tabLayout.newTab().setCustomView(tabImageView), position);
    }

}
