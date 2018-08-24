package com.welab.league.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.welab.league.R;
import com.welab.league.api.weblab.response.NewJoiningTeamInfo;
import com.welab.league.widget.BaseViewHolder;

public class NewJoiningTeamViewHolder extends BaseViewHolder<NewJoiningTeamInfo> {

    private ImageView mEmblemImageView;
    private TextView mTeamNameTextView;
    private TextView mMemberCountTextView;

    public NewJoiningTeamViewHolder(Context context, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_joinning_team_item, parent, false));
    }

    public NewJoiningTeamViewHolder(View itemView) {
        super(itemView);

        mEmblemImageView = (ImageView) itemView.findViewById(R.id.emblem_imageview);
        mTeamNameTextView = (TextView) itemView.findViewById(R.id.team_name_textview);
        mMemberCountTextView = (TextView) itemView.findViewById(R.id.member_count_textview);
    }

    @Override
    public void setData(NewJoiningTeamInfo newJoiningTeamInfo) {
        mTeamNameTextView.setText(newJoiningTeamInfo.getTeamName());
        mMemberCountTextView.setText(newJoiningTeamInfo.getTeamMemberCount());
    }
}
