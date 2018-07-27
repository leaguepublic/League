package com.welab.league.adapter.holder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.api.weblab.response.risingteam.RisingTeam;
import com.welab.league.widget.BaseViewHolder;

public class RisingTeamViewHolder extends BaseViewHolder<RisingTeam> {

    public RisingTeamViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.match_team_info_item, parent, false));
    }

    public RisingTeamViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(RisingTeam risingTeam) {
        Log.e("TAG", "LJS== risingTeam : " + risingTeam);

    }
}
