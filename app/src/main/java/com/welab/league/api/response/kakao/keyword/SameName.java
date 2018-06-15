package com.welab.league.api.response.kakao.keyword;

import java.util.ArrayList;

public class SameName {
    public ArrayList<Region> region; // 질의어에서 인식된 지역의 리스트. '중앙로 맛집' 에서 중앙로에 해당하는 지역 리스트
    public String keyword; // 질의어에서 지역 정보를 제외한 키워드. '중앙로 맛집' 에서 '맛집'
    public String selected_region; // 인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보.

    @Override
    public String toString() {
        StringBuilder logBuff = new StringBuilder();

        logBuff.append("\nSameName >>");
        logBuff.append(region);
        logBuff.append("\nkeyword : " + keyword);
        logBuff.append("\nselected_region : " + selected_region);
        logBuff.append("\nSameName <<");

        return logBuff.toString();
    }
}
