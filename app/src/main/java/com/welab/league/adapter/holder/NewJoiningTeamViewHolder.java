package com.welab.league.adapter.holder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welab.league.R;
import com.welab.league.api.weblab.response.NewJoiningTeam;
import com.welab.league.widget.BaseViewHolder;

public class NewJoiningTeamViewHolder extends BaseViewHolder<NewJoiningTeam> {

    public NewJoiningTeamViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.match_team_info_item, parent, false));
    }

    public NewJoiningTeamViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(NewJoiningTeam newJoiningTeam) {
        Log.e("TAG", "LJS== newJoiningTeam : " + newJoiningTeam);

    }
}
