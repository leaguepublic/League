package com.welab.league.api.kakao.keyword;

public class Meta {
    public SameName same_name;
    public int pageable_count;
    public int total_count;
    public boolean is_end;

    @Override
    public String toString() {
        StringBuilder logBuff = new StringBuilder();
        logBuff.append("\nMeta >>");
        logBuff.append("\nsame_name : " + same_name);
        logBuff.append("\npageable_count : " + pageable_count);
        logBuff.append("\ntotal_count : " + total_count);
        logBuff.append("\nis_end : " + is_end);
        logBuff.append("\nMeta <<");

        return logBuff.toString();
    }
}
