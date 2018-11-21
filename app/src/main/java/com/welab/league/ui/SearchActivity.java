package com.welab.league.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.welab.league.R;
import com.welab.league.adapter.SearchResultListAdapter;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.db.DbUtils;
import com.welab.league.factory.DataConverter;
import com.welab.league.util.Utils;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private View mDeleteKeywordView;
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

        mDeleteKeywordView = findViewById(R.id.delete_imagebutton);
        mSearchEditText = (EditText) findViewById(R.id.search_edittext);
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    mDeleteKeywordView.setVisibility(View.VISIBLE);
                } else {
                    mDeleteKeywordView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDbUtils = DbUtils.getInstance(this);
        mRecentKeywordList = DataConverter.getRecentKeywordList(mDbUtils.selectKeywordInfo());

        mSearchResultListAdapter = new SearchResultListAdapter(this, mRecentKeywordList);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Utils.getDrawable(this, R.drawable.recyclerview_divider));

        mSearchListView = (RecyclerView) findViewById(R.id.search_listview);
        mSearchListView.addItemDecoration(dividerItemDecoration);
        mSearchListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSearchListView.setAdapter(mSearchResultListAdapter);

        setCautionLayoutVisibity(mRecentKeywordList.size() < 1);
    }

    public void onClickCancel(View view) {
        finish();
    }

    public void onClickDeleteKeyword(View view) {
        mSearchEditText.setText("");
    }

    public void onClickDeleteAllSearchKeywords(View view) {
        mDbUtils.deleteAllKeywords();

        int keywordSize = mRecentKeywordList.size();

        mRecentKeywordList.clear();

        mSearchResultListAdapter.notifyItemRangeRemoved(0, keywordSize);

        setCautionLayoutVisibity(mRecentKeywordList.size() < 1);
    }

    public void onClickSearch(View view) {
        String searchKeyword = mSearchEditText.getText().toString();

        mDbUtils.insertKeyword(searchKeyword);

        // 검색결과 화면
//        mSearchResultListAdapter.setItemList();

        finish();
    }

    private void setCautionLayoutVisibity(boolean isVisible) {
        if (isVisible == true) {
            findViewById(R.id.search_caution_layout).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.search_caution_layout).setVisibility(View.GONE);
        }
    }
}
