package com.welab.league.factory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.welab.league.adapter.holder.LocalFilterViewHolder;
import com.welab.league.adapter.holder.MatchViewHolder;
import com.welab.league.api.response.weblab.BaseItemInfo;

public class ViewHolderFactory {

    public static final int VIEW_TYPE_NONE = -1;
    public static final int VIEW_TYPE_MATCH = 0; // 매치 신청
    public static final int VIEW_TYPE_MATCH_RESULT_BALLOT = 1; // 승부 예측
    public static final int VIEW_TYPE_MATCH_RESULT = 2;
    public static final int VIEW_TYPE_NEW_JOINING_TEAM = 3; // 신규 팀
    public static final int VIEW_TYPE_RISING_TEAM = 4; // 요즘 뜨 팀
    public static final int VIEW_TYPE_LEAGUE_RANK = 5; // 리그 순위
    public static final int VIEW_TUPE_LOCAL_FILTER = 6; // 지역 필터

    private static class Singleton {
        private static final ViewHolderFactory INSTANCE = new ViewHolderFactory();
    }

    private ViewHolderFactory() {}

    public static ViewHolderFactory getInstance() {
        return Singleton.INSTANCE;
    }

    public RecyclerView.ViewHolder get(Context context, ViewGroup parent, int type) {
        RecyclerView.ViewHolder viewHolder = null;

        Log.e("TAG", "LJS== type : " + type);

        switch (type) {
            case VIEW_TYPE_MATCH:
                viewHolder = new MatchViewHolder(context, parent);
                break;

            case VIEW_TYPE_MATCH_RESULT_BALLOT:
                break;

            case VIEW_TYPE_MATCH_RESULT:
                break;

            case VIEW_TYPE_NEW_JOINING_TEAM:
                break;

            case VIEW_TYPE_RISING_TEAM:
                break;

            case VIEW_TYPE_LEAGUE_RANK:
                break;

            case VIEW_TUPE_LOCAL_FILTER:
                viewHolder = new LocalFilterViewHolder(context, parent);
                break;
        }

        Log.e("TAG", "LJS== viewHolder : " + viewHolder);

        return viewHolder;
    }

    public <T> void setData(RecyclerView.ViewHolder viewHolder, T itemInfo) {
        switch (((BaseItemInfo) itemInfo).getType()) {
            case VIEW_TYPE_MATCH:
                break;

            case VIEW_TYPE_MATCH_RESULT_BALLOT:
                break;

            case VIEW_TYPE_MATCH_RESULT:
                break;

            case VIEW_TYPE_NEW_JOINING_TEAM:
                break;

            case VIEW_TYPE_RISING_TEAM:
                break;

            case VIEW_TYPE_LEAGUE_RANK:
                break;

//            case VIEW_TUPE_LOCAL_FILTER:
//                break;
        }
    }
}
