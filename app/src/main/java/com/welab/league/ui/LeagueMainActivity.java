package com.welab.league.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.welab.league.R;
import com.welab.league.adapter.MainTabFragmentPagerAdapter;
import com.welab.league.data.TabData;
import com.welab.league.factory.TabFactory;
import com.welab.league.listener.OnCallViewListener;
import com.welab.league.listener.OnResultListener;
import com.welab.league.widget.FilterLayout;

import java.util.ArrayList;

import static com.welab.league.factory.ViewFactory.VIEW_TYPE_HOME;
import static com.welab.league.factory.ViewFactory.VIEW_TYPE_MATCH_RESULT_BALLOT;
import static com.welab.league.factory.ViewFactory.VIEW_TYPE_MATCH_TEAM;
import static com.welab.league.factory.ViewFactory.VIEW_TYPE_NEW_JOINING_TEAM;
import static com.welab.league.factory.ViewFactory.VIEW_TYPE_NOTI;

public class LeagueMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnCallViewListener {

    private DrawerLayout mSlidingMenu;
    private ViewPager mViewPager;
    private MainTabFragmentPagerAdapter mMainTabFragmentPagerAdapter;
    private FilterLayout mFilterLayout;

    public static void open(Context context) {
        context.startActivity(new Intent(context, LeagueMainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_main);

        // Tab
        // https://stackverflow.com/questions/9172504/how-to-style-the-divider-between-ice-cream-sandwich-tabs - Tab의 Divider를 넣었을 때 이슈 처리
        // https://stackoverflow.com/questions/30796710/tablayout-tab-selection - Tab 강제 선택 방법
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ArrayList<TabData> tabDataList = new ArrayList<>();
        tabDataList.add(new TabData(R.string.tab_home, "", VIEW_TYPE_HOME));
        tabDataList.add(new TabData(R.string.tab_match, "", VIEW_TYPE_MATCH_TEAM));
        tabDataList.add(new TabData(R.string.tab_team, "", VIEW_TYPE_NEW_JOINING_TEAM));
        tabDataList.add(new TabData(R.string.tab_match_result_ballot, "", VIEW_TYPE_MATCH_RESULT_BALLOT));
        tabDataList.add(new TabData(R.string.tab_noti, "", VIEW_TYPE_NOTI));

        TabFactory tabFactory = new TabFactory();
        tabFactory.setTab(this, tabLayout, tabDataList);

        // https://stackoverflow.com/questions/30923889/flinging-with-recyclerview-appbarlayout
        // 스크롤 이슈.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mMainTabFragmentPagerAdapter = new MainTabFragmentPagerAdapter(getSupportFragmentManager(), tabDataList.size());

        mViewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        mViewPager.setOffscreenPageLimit(tabDataList.size());
        mViewPager.setAdapter(mMainTabFragmentPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(mOnTabSelectedListener);

        // Sliding menu
        mSlidingMenu = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mSlidingMenu, toolbar, R.string.drawer_open, R.string.drawer_close);
        mSlidingMenu.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFilterLayout = (FilterLayout) findViewById(R.id.filter_layout);
    }

    @Override
    public void onBackPressed() {
        if (mFilterLayout == null || mFilterLayout.close() == false) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.create_team:
                CreateTeamActivity.open(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mSlidingMenu.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCallView(VIEW_TYPE viewType, final ArrayList<String> SELECTED_DATA_LIST, OnResultListener onResultListener) {
        switch (viewType) {
            case LOCAL_NAME_VIEW:
                ArrayList<String> localNameList = new ArrayList<>();
                localNameList.add("서대문구");
                localNameList.add("은평구");
                localNameList.add("서초구");
                localNameList.add("강남구");
                localNameList.add("노원구");
                localNameList.add("성동구");
                localNameList.add("마포구");
                localNameList.add("용산구");

                mFilterLayout.setData(localNameList, SELECTED_DATA_LIST);
                mFilterLayout.setOkButtonListener(view -> {
                    onResultListener.onResult(SELECTED_DATA_LIST);
                });

                break;
        }
    }

    private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };
}
