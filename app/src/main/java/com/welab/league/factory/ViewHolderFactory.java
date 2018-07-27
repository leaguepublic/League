package com.welab.league.factory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.welab.league.adapter.holder.ListViewHolder;
import com.welab.league.adapter.holder.LocalFilterViewHolder;
import com.welab.league.adapter.holder.MatchResultViewHolder;
import com.welab.league.adapter.holder.MatchTeamViewHolder;
import com.welab.league.adapter.holder.NewJoiningTeamViewHolder;
import com.welab.league.adapter.holder.RisingTeamViewHolder;
import com.welab.league.widget.BaseViewHolder;

import java.util.List;

public class ViewHolderFactory {

    public static final int VIEW_TYPE_NONE = -1;
    public static final int VIEW_TYPE_TABHOME = 0; // 탭 홈
    public static final int VIEW_TYPE_MATCH = 1; // 매치 신청
    public static final int VIEW_TYPE_MATCH_RESULT_BALLOT = 2; // 승부 예측
    public static final int VIEW_TYPE_MATCH_RESULT = 3;
    public static final int VIEW_TYPE_NEW_JOINING_TEAM = 4; // 신규 팀
    public static final int VIEW_TYPE_RISING_TEAM = 5; // 요즘 뜨 팀
    public static final int VIEW_TYPE_LEAGUE_RANK = 6; // 리그 순위
    public static final int VIEW_TYPE_LOCAL_FILTER = 7; // 지역 필터
    public static final int VIEW_TYPE_LIST = 8;

    private static class Singleton {
        private static final ViewHolderFactory INSTANCE = new ViewHolderFactory();
    }

    private ViewHolderFactory() {}

    public static ViewHolderFactory getInstance() {
        return Singleton.INSTANCE;
    }

    public RecyclerView.ViewHolder get(Context context, ViewGroup parent, int type) {
        BaseViewHolder viewHolder = null;

        switch (type) {
            case VIEW_TYPE_MATCH:
                viewHolder = new MatchTeamViewHolder(context, parent);
                break;

            case VIEW_TYPE_MATCH_RESULT_BALLOT:
                break;

            case VIEW_TYPE_MATCH_RESULT:
                viewHolder = new MatchResultViewHolder(context, parent);
                break;

            case VIEW_TYPE_NEW_JOINING_TEAM:
                viewHolder = new NewJoiningTeamViewHolder(context, parent);
                break;

            case VIEW_TYPE_RISING_TEAM:
                viewHolder = new RisingTeamViewHolder(context, parent);
                break;

            case VIEW_TYPE_LEAGUE_RANK:
                break;

            case VIEW_TYPE_LOCAL_FILTER:
                viewHolder = new LocalFilterViewHolder(context, parent);
                break;

            case VIEW_TYPE_LIST:
                viewHolder = new ListViewHolder(context, parent);
                break;
        }

        return viewHolder;
    }

    public <T> void setData(RecyclerView.ViewHolder viewHolder, T itemInfo) {
        ((BaseViewHolder) viewHolder).setData(itemInfo);
    }

    public <T> void setListData(RecyclerView.ViewHolder viewHolder, List<T> itemInfoList) {
        ((BaseViewHolder) viewHolder).setListData(itemInfoList);
    }
}
