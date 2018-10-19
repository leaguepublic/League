package com.welab.league.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sweetsound.polygongraph.PolygonGraphView;
import com.welab.league.R;
import com.welab.league.api.weblab.response.risingteam.RisingTeam;
import com.welab.league.factory.Navigator;
import com.welab.league.widget.BaseFragment;

import java.util.List;

public class RisingTeamFragment extends BaseFragment<RisingTeam> {

    private TextView mTeamNameTextView;
    private TextView mTeamStatsTextView;
    private TextView mTeamMemberCountTextView;
    private TextView mTeamAgeTextView;
    private Button mMatchButton;
    private PolygonGraphView mPolygonGraphView;

    private RisingTeam mRisingTeam;

    public RisingTeamFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.rising_team_item, container, false);

        mTeamNameTextView = (TextView) rootView.findViewById(R.id.team_name_textview);
        mTeamStatsTextView = (TextView) rootView.findViewById(R.id.team_stats_textview);
        mTeamMemberCountTextView = (TextView) rootView.findViewById(R.id.team_member_count_textview);
        mTeamAgeTextView = (TextView) rootView.findViewById(R.id.team_age_textview);
        mMatchButton = (Button) rootView.findViewById(R.id.match_button);
//        mPolygonGraphView = (PolygonGraphView) rootView.findViewById(R.id.polygongraphview);

        Log.e("TAG", "LJS== mRisingTeam : " + mRisingTeam);

        mMatchButton.setOnClickListener(view -> {
            Navigator.requestMatch(mRisingTeam.getTeamCode());
        });

        mTeamNameTextView.setText(mRisingTeam.getTeamName());
        mTeamMemberCountTextView.setText(mRisingTeam.getTeamMemberCount());
        mTeamAgeTextView.setText(mRisingTeam.getTeamAge());

        List<String> teamStatsList = mRisingTeam.getTeamStats();
        mTeamStatsTextView.setText(getContext().getString(R.string.team_stats, teamStatsList.get(0), teamStatsList.get(1), teamStatsList.get(2)));

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setData(RisingTeam risingTeam) {
        mRisingTeam = risingTeam;
    }
}
