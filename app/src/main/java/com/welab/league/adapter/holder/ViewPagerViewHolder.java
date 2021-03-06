package com.welab.league.adapter.holder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.adapter.ViewPagerAdapter;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.api.weblab.response.ViewPagerItemInfo;
import com.welab.league.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerViewHolder extends BaseViewHolder<ViewPagerItemInfo> {

    private final int MAX_DISPLAY_COUNT = 2;

    private TextView mTitleTextView;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private LinearLayout mIndicatorContainView;

    private ArrayList<BaseItemInfo> mListDataList = new ArrayList<>();

    public ViewPagerViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_viewholder_layout, parent, false));
    }

    public ViewPagerViewHolder(View itemView) {
        super(itemView);

        mIndicatorContainView = (LinearLayout) itemView.findViewById(R.id.indicator_container);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title_textview);

        mViewPagerAdapter = new ViewPagerAdapter(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), mListDataList);

        mViewPager = (ViewPager) itemView.findViewById(R.id.body_viewpager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    public void setListData(List<ViewPagerItemInfo> listDataList) {
        mListDataList.clear();

        for (int i = 0; i < MAX_DISPLAY_COUNT; i++) {
            mListDataList.add(listDataList.get(i));
        }

        int displayCount = mListDataList.size();

        mIndicatorContainView.removeAllViews();

        for (int i = 0; i < displayCount; i++) {
            mIndicatorContainView.addView(LayoutInflater.from(mIndicatorContainView.getContext()).inflate(R.layout.indicator_icon, mIndicatorContainView, false));
        }

        setIndicator(0);

        mViewPager.setOffscreenPageLimit(displayCount);

        if (mViewPagerAdapter != null) {
            mViewPagerAdapter.notifyDataSetChanged();
        }

        mTitleTextView.setText(listDataList.get(0).getTitle());

    }

    private void setIndicator(int selectIndex) {
        int childCount = mIndicatorContainView.getChildCount();

        for (int i = 0; i < childCount; i++) {
            if (selectIndex == i) {
                ((ImageView) mIndicatorContainView.getChildAt(i)).setImageResource(R.drawable.ico_indicator_s);
            } else {
                ((ImageView) mIndicatorContainView.getChildAt(i)).setImageResource(R.drawable.ico_indicator_n);
            }
        }
    }
}
