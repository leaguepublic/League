package com.welab.league.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.api.weblab.response.matchresult.MatchResult;
import com.welab.league.api.weblab.response.matchresult.Team;
import com.welab.league.widget.BaseFragment;

import java.util.List;

public class MatchResultFragment extends BaseFragment<MatchResult> {

    private final int LEFT_TEAM_INDEX = 0;
    private final int RIGHT_TEAM_INDEX = 1;

    private View mLeftTeamInfoView;
    private View mRigthTeamInfoView;
    private TextView mLeftTeamPointTextView;
    private TextView mRightTeamPointTextView;
    private TextView mMatchDateTextView;
    private TextView mMatchGroundTextView;

    public MatchResultFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.match_result_item, container, false);

        mLeftTeamInfoView = rootView.findViewById(R.id.left_team_info_layout);
        mRigthTeamInfoView = rootView.findViewById(R.id.right_team_info_layout);

        mLeftTeamPointTextView = (TextView) rootView.findViewById(R.id.left_team_point_textview);
        mRightTeamPointTextView = (TextView) rootView.findViewById(R.id.right_team_point_textview);

        mMatchDateTextView = (TextView) rootView.findViewById(R.id.match_date_textview);
        mMatchGroundTextView = (TextView) rootView.findViewById(R.id.match_ground_textview);

        MatchResult matchResult = getData();

        mMatchDateTextView.setText(matchResult.getMatchDate());
        mMatchGroundTextView.setText(matchResult.getMatchGround());

        List<Team> teamList = matchResult.getTeams();
        Team leftTeamInfo = teamList.get(LEFT_TEAM_INDEX);
        Team rightTeamInfo = teamList.get(RIGHT_TEAM_INDEX);

//        mLeftTeamPointTextView.setText(leftTeamInfo.getMatchRecord());
        setTeamInfo(mLeftTeamInfoView, leftTeamInfo);

//        mRightTeamPointTextView.setText(rightTeamInfo.getMatchRecord());
        setTeamInfo(mRigthTeamInfoView, rightTeamInfo);

        return rootView;
    }

    private void setTeamInfo(View teamInfoLayout, Team teamInfo) {
        ((TextView) teamInfoLayout.findViewById(R.id.team_name_textview)).setText(teamInfo.getTeamName());
//        ((ImageView) teamInfoLayout.findViewById(R.id.team_elblem_imageview))

    }
}
