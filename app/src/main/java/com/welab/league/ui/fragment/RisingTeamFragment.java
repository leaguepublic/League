package com.welab.league.ui.fragment;


import android.os.Bundle;
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
        mPolygonGraphView = (PolygonGraphView) rootView.findViewById(R.id.polygongraphview);

        final RisingTeam risingTeam = getData();

        mMatchButton.setOnClickListener(view -> {
            Navigator.requestMatch(risingTeam.getTeamCode());
        });

        mTeamNameTextView.setText(risingTeam.getTeamName());
        mTeamMemberCountTextView.setText(risingTeam.getTeamMemberCount());
        mTeamAgeTextView.setText(risingTeam.getTeamAge());

        List<String> teamStatsList = risingTeam.getTeamStats();
        mTeamStatsTextView.setText(getContext().getString(R.string.team_stats, teamStatsList.get(0), teamStatsList.get(1), teamStatsList.get(2)));

        mPolygonGraphView.setGraphAngleList(new float[]{2300, 1400, 5300, 8700, 2400});

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
