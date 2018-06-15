package com.welab.league.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.welab.league.R;

public class LeagueMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final int TAB_COUNT = 5;

    private DrawerLayout mSlidingMenu;

    public static void open(Context context) {
        context.startActivity(new Intent(context, LeagueMainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_main);

        // https://stackoverflow.com/questions/30923889/flinging-with-recyclerview-appbarlayout
        // 스크롤 이슈.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Tab
        // https://stackverflow.com/questions/9172504/how-to-style-the-divider-between-ice-cream-sandwich-tabs
        // Tab의 Divider를 넣었을 때 이슈 처리
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_home)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_match)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_team)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_victory)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_noti)));
        setTabTitle(tabLayout, getString(R.string.tab_home));
        setTabTitle(tabLayout, getString(R.string.tab_match));
        setTabTitle(tabLayout, getString(R.string.tab_team));
        setTabTitle(tabLayout, getString(R.string.tab_victory));
        setTabTitle(tabLayout, getString(R.string.tab_noti));

        // Sliding menu
        mSlidingMenu = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mSlidingMenu, toolbar, R.string.drawer_open, R.string.drawer_close);
        mSlidingMenu.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            case R.id.menu_my:
                Log.e("TAG", "LJS== menu MY ==");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mSlidingMenu.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setTabTitle(TabLayout tabLayout, String title) {
        TextView tabTitleTextView = (TextView) this.getLayoutInflater().inflate(R.layout.tab_textview, null);
        tabTitleTextView.setText(title);

        tabLayout.addTab(tabLayout.newTab().setCustomView(tabTitleTextView));
    }
}
