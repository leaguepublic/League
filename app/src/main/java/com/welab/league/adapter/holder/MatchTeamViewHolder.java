package com.welab.league.adapter.holder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.api.weblab.response.MatchTeamInfo;
import com.welab.league.widget.BaseViewHolder;

public class MatchTeamViewHolder extends BaseViewHolder<MatchTeamInfo> {

    private TextView mTeamNameTextView;
    private TextView mMatchgroundTextview;

    public MatchTeamViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.match_team_info_item, parent, false));
    }

    public MatchTeamViewHolder(View itemView) {
        super(itemView);

        mTeamNameTextView = (TextView) itemView.findViewById(R.id.team_name_textview);
        mMatchgroundTextview = (TextView) itemView.findViewById(R.id.matchground_textview);
    }

    @Override
    public void setData(MatchTeamInfo matchTeamInfo) {
        Log.e("TAG", "LJS== matchTeamInfo : " + matchTeamInfo);

        mTeamNameTextView.setText(matchTeamInfo.getTeamName());
        mMatchgroundTextview.setText(matchTeamInfo.getMatchGround());
    }
}
