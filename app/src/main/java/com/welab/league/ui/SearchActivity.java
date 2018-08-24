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
import com.welab.league.adapter.SearchResultListAdapter;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.db.DbUtils;
import com.welab.league.factory.DataConverter;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText mSearchEditText;

    private DbUtils mDbUtils;

    private List<BaseItemInfo> mRecentKeywordList;

    private RecyclerView mSearchListView;
    private SearchResultListAdapter mSearchResultListAdapter;

    public static void open(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchEditText = (EditText) findViewById(R.id.search_edittext);

        mDbUtils = DbUtils.getInstance(this);
        mRecentKeywordList = DataConverter.getRecentKeywordList(mDbUtils.selectKeywordInfo());

        mSearchResultListAdapter = new SearchResultListAdapter(this, mRecentKeywordList);

        mSearchListView = (RecyclerView) findViewById(R.id.search_listview);
        mSearchListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSearchListView.setAdapter(mSearchResultListAdapter);
    }

    public void onClickCancel(View view) {
        finish();
    }

    public void onClickDeleteAllSearchKeywords(View view) {
        mDbUtils.deleteAllKeywords();

        int keywordSize = mRecentKeywordList.size();

        mRecentKeywordList.clear();

        mSearchResultListAdapter.notifyItemRangeRemoved(0, keywordSize);
    }

    public void onClickSearch(View view) {
        String searchKeyword = mSearchEditText.getText().toString();

        mDbUtils.insertKeyword(searchKeyword);

        // 검색결과 화면
//        mSearchResultListAdapter.setItemList();
    }
}
