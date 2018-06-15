package com.welab.league.api.response.kakao.keyword;

import java.util.ArrayList;

public class SearchResultByKeyword {
    public Meta meta;
    public ArrayList<Documents> documents;

    @Override
    public String toString() {
        StringBuilder logBuff = new StringBuilder();

        logBuff.append("\nSearchResultByKeyword >>");
        logBuff.append(meta.toString());
        logBuff.append(documents);
        logBuff.append("\nSearchResultByKeyword <<");

        return logBuff.toString();
    }
}
