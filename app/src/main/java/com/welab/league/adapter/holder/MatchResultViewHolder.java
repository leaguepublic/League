package com.welab.league.adapter.holder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.api.weblab.response.matchresult.MatchResult;
import com.welab.league.widget.BaseViewHolder;

public class MatchResultViewHolder extends BaseViewHolder<MatchResult> {

    public MatchResultViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.match_result_item, parent, false));
    }

    public MatchResultViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(MatchResult matchResult) {
        Log.e("TAG", "LJS== matchResult : " + matchResult);
    }
}
