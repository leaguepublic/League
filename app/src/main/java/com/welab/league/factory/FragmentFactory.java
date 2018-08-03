package com.welab.league.factory;

import android.support.v4.app.Fragment;

import com.welab.league.ui.fragment.CategoryFragment;
import com.welab.league.ui.fragment.HomeFragment;
import com.welab.league.ui.fragment.MatchFragment;
import com.welab.league.ui.fragment.MatchResultBallotFragment;
import com.welab.league.ui.fragment.NotiFragment;
import com.welab.league.ui.fragment.TeamFragment;

import static com.welab.league.factory.TabFactory.TAB_INDEX_HOME;
import static com.welab.league.factory.TabFactory.TAB_INDEX_MATCH;
import static com.welab.league.factory.TabFactory.TAB_INDEX_MATCH_RESULT_BALLOT;
import static com.welab.league.factory.TabFactory.TAB_INDEX_NOTI;
import static com.welab.league.factory.TabFactory.TAB_INDEX_TEAM;

public class FragmentFactory {

    private static class Singleton {
        public static final FragmentFactory INSTANCE = new FragmentFactory();
    }

    private FragmentFactory() {}

    public static FragmentFactory getInstance() {
        return Singleton.INSTANCE;
    }

    public Fragment getMainTabFragment(int type) {
        Fragment fragment = null;

        switch (type) {
            case TAB_INDEX_HOME:
                fragment = new HomeFragment();
                break;

            case TAB_INDEX_MATCH:
                fragment = new MatchFragment();
                break;

            case TAB_INDEX_TEAM:
                fragment = new TeamFragment();
                break;

            case TAB_INDEX_MATCH_RESULT_BALLOT:
                fragment = new MatchResultBallotFragment();
                break;

            case TAB_INDEX_NOTI:
                fragment = new NotiFragment();
                break;
        }

        return fragment;
    }

    public Fragment getCategoryFragment() {
        return \new CategoryFragment();
    }
}
