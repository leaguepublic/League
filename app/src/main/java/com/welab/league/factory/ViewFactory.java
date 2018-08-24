package com.welab.league.factory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.welab.league.adapter.holder.CategoryViewHolder;
import com.welab.league.adapter.holder.DividerViewHolder;
import com.welab.league.adapter.holder.ListViewHolder;
import com.welab.league.adapter.holder.LocalFilterViewHolder;
import com.welab.league.adapter.holder.MatchResultViewHolder;
import com.welab.league.adapter.holder.MatchTeamViewHolder;
import com.welab.league.adapter.holder.MoreViewHolder;
import com.welab.league.adapter.holder.NewJoiningTeamViewHolder;
import com.welab.league.adapter.holder.RisingTeamViewHolder;
import com.welab.league.adapter.holder.ViewPagerViewHolder;
import com.welab.league.api.weblab.response.BaseItemInfo;
import com.welab.league.ui.fragment.HomeFragment;
import com.welab.league.ui.fragment.MatchTeamFragment;
import com.welab.league.ui.fragment.MatchResultBallotFragment;
import com.welab.league.ui.fragment.MatchResultFragment;
import com.welab.league.ui.fragment.NotiFragment;
import com.welab.league.ui.fragment.RisingTeamFragment;
import com.welab.league.ui.fragment.TeamFragment;
import com.welab.league.widget.BaseFragment;
import com.welab.league.widget.BaseViewHolder;

import java.util.List;

public class ViewFactory {
    public static final int VIEW_TYPE_NONE = -1;
    public static final int VIEW_TYPE_HOME = 0; // 탭 홈
    public static final int VIEW_TYPE_MATCH_TEAM = 1; // 매치 신청
    public static final int VIEW_TYPE_NEW_JOINING_TEAM = 2; // 신규 팀
    public static final int VIEW_TYPE_MATCH_RESULT_BALLOT = 3; // 승부 예측
    public static final int VIEW_TYPE_NOTI = 4; // 알림
    public static final int VIEW_TYPE_MATCH_RESULT = 5; // 경기 결과
    public static final int VIEW_TYPE_RISING_TEAM = 6; // 요즘 뜨 팀
    public static final int VIEW_TYPE_LEAGUE_RANK = 7; // 리그 순위
    public static final int VIEW_TYPE_LOCAL_FILTER = 8; // 지역 필터
    public static final int VIEW_TYPE_DIVIDER = 9;
    public static final int VIEW_TYPE_MORE = 10; // 더 보기
    public static final int VIEW_TYPE_LIST = 11; // List Type
    public static final int VIEW_TYPE_VIEWPAGER = 12; // ViewPager Type
    public static final int VIEW_TYPE_CATEGORY = 13; // Category Type
    public static final int VIEW_TYPE_RECENT_KEYWORD = 14;

    private static class Singleton {
        private static final ViewFactory INSTANCE = new ViewFactory();
    }

    private ViewFactory() {}

    public static ViewFactory getInstance() {
        return ViewFactory.Singleton.INSTANCE;
    }

    public RecyclerView.ViewHolder getViewHodler(Context context, ViewGroup parent, int type) {
        BaseViewHolder viewHolder = null;

        switch (type) {
            case VIEW_TYPE_MATCH_TEAM:
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

            case VIEW_TYPE_DIVIDER:
                viewHolder = new DividerViewHolder(context, parent);
                break;

            case VIEW_TYPE_MORE:
                viewHolder = new MoreViewHolder(context, parent);
                break;

            case VIEW_TYPE_LIST:
                viewHolder = new ListViewHolder(context, parent);
                break;

            case VIEW_TYPE_VIEWPAGER:
                viewHolder = new ViewPagerViewHolder(context, parent);
                break;

            case VIEW_TYPE_CATEGORY:
                viewHolder = new CategoryViewHolder(context, parent);
                break;
        }

        return viewHolder;
    }

    public int getListType(List<BaseItemInfo> targetList) {
        int type = VIEW_TYPE_LIST;

        switch (targetList.get(0).getType()) {
            case VIEW_TYPE_MATCH_TEAM:
            case VIEW_TYPE_LEAGUE_RANK:
            case VIEW_TYPE_NEW_JOINING_TEAM:
                type = VIEW_TYPE_LIST;
                break;

            case VIEW_TYPE_MATCH_RESULT_BALLOT:
            case VIEW_TYPE_MATCH_RESULT:
                type = VIEW_TYPE_VIEWPAGER;
                break;

            case VIEW_TYPE_RISING_TEAM:
                type = VIEW_TYPE_CATEGORY;
                break;
        }

        return type;
    }

    public <T> void setData(RecyclerView.ViewHolder viewHolder, T itemInfo) {
        ((BaseViewHolder) viewHolder).setData(itemInfo);
    }

    public <T> void setListData(RecyclerView.ViewHolder viewHolder, List<T> itemInfoList) {
        ((BaseViewHolder) viewHolder).setListData(itemInfoList);
    }

    public BaseFragment getFragment(int type) {
        BaseFragment fragment = null;

        switch (type) {
            case VIEW_TYPE_HOME:
                fragment = new HomeFragment();
                break;

            case VIEW_TYPE_MATCH_TEAM:
                fragment = new MatchTeamFragment();
                break;

            case VIEW_TYPE_NEW_JOINING_TEAM:
                fragment = new TeamFragment();
                break;

            case VIEW_TYPE_MATCH_RESULT_BALLOT:
                fragment = new MatchResultBallotFragment();
                break;

            case VIEW_TYPE_NOTI:
                fragment = new NotiFragment();
                break;

            case VIEW_TYPE_MATCH_RESULT:
                fragment = new MatchResultFragment();
                break;
            case VIEW_TYPE_RISING_TEAM:
                fragment = new RisingTeamFragment();
                break;

            case VIEW_TYPE_CATEGORY:
                fragment = new RisingTeamFragment();
                break;
        }

        return fragment;
    }

    public void callActivity(int type) {

    }
}
