package com.welab.league.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.api.weblab.response.MatchTeamInfo;
import com.welab.league.factory.Navigator;
import com.welab.league.widget.BaseViewHolder;

import java.util.List;

public class MatchTeamViewHolder extends BaseViewHolder<MatchTeamInfo> {

    private TextView mTeamNameTextView;
    private TextView mMatchgroundTextview;
    private TextView mMatchDateTextview;
    private TextView mTeamStatsTextView;
    private TextView mTeamAgeTextView;
    private TextView mTeamTypeTextView;
    private TextView mTeamRankingTextView;
    private RatingBar mTeamMannerRatingBar;
    private Button mMatchButton;

    private Context mContext;

    public MatchTeamViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.match_team_info_item, parent, false));

        mContext = context;
    }

    public MatchTeamViewHolder(View itemView) {
        super(itemView);

        mTeamNameTextView = (TextView) itemView.findViewById(R.id.team_name_textview);
        mMatchgroundTextview = (TextView) itemView.findViewById(R.id.matchground_textview);
        mMatchDateTextview = (TextView) itemView.findViewById(R.id.match_date_textview);
        mTeamStatsTextView = (TextView) itemView.findViewById(R.id.team_stats_textview);
        mTeamAgeTextView = (TextView) itemView.findViewById(R.id.team_age_textview);
        mTeamTypeTextView = (TextView) itemView.findViewById(R.id.team_type_textview);
        mTeamRankingTextView = (TextView) itemView.findViewById(R.id.team_ranking_textview);
        mTeamMannerRatingBar = (RatingBar) itemView.findViewById(R.id.team_manner_ratingbar);
        mMatchButton = (Button) itemView.findViewById(R.id.match_button);
    }

    @Override
    public void setData(MatchTeamInfo matchTeamInfo) {
        mMatchButton.setOnClickListener(view -> {
            Navigator.requestMatch(matchTeamInfo.getTeamCode());
        });

        mTeamNameTextView.setText(matchTeamInfo.getTeamName());
        mMatchgroundTextview.setText(matchTeamInfo.getMatchGround());
        mMatchDateTextview.setText(matchTeamInfo.getMatchDate());
        mTeamStatsTextView.setText(getTeamStats(matchTeamInfo.getTeamStats()));
        mTeamAgeTextView.setText(matchTeamInfo.getTeamAge());
        mTeamTypeTextView.setText(matchTeamInfo.getTeamType());
        mTeamRankingTextView.setText(mContext.getString(R.string.team_level, matchTeamInfo.getTeamRanking()));
        mTeamMannerRatingBar.setRating(3.5f);
    }

    private String getTeamStats(List<String> dataList) {
        return mContext.getString(R.string.team_stats, dataList.get(0), dataList.get(1), dataList.get(2));
    }
}
