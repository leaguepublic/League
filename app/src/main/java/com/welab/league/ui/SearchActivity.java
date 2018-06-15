package com.welab.league.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.welab.league.R;
import com.welab.league.adapter.RecentKeywordListAdapter;
import com.welab.league.db.DbUtils;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText mSearchEditText;

    private DbUtils mDbUtils;

    private List<String> mRecentKeywordList;

    private RecyclerView mRecentKeywordListView;
    private RecentKeywordListAdapter mRecentKeywordListAdapter;

    public static void open(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchEditText = (EditText) findViewById(R.id.search_edittext);

        mDbUtils = DbUtils.getInstance(this);
        mRecentKeywordList = mDbUtils.selectKeywordInfo();

        mRecentKeywordListAdapter = new RecentKeywordListAdapter(this, mRecentKeywordList);

        mRecentKeywordListView = (RecyclerView) findViewById(R.id.recent_search_keyword_listview);
        mRecentKeywordListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecentKeywordListView.setAdapter(mRecentKeywordListAdapter);
    }

    public void onClickCancel(View view) {
        finish();
    }

    public void onClickDeleteAllSearchKeywords(View view) {
        mDbUtils.deleteAllKeywords();

        int keywordSize = mRecentKeywordList.size();

        mRecentKeywordList.clear();

        mRecentKeywordListAdapter.notifyItemRangeRemoved(0, keywordSize);
    }

    public void onClickSearch(View view) {
        String searchKeyword = mSearchEditText.getText().toString();

        mDbUtils.insertKeyword(searchKeyword);

        // 검색결과 화면
    }
}
