package com.welab.league.api.naver;

public class UserProfile {
    public String resultcode;
    public String message;
    public Response response;

    @Override
    public String toString() {
        StringBuilder logBuff = new StringBuilder();
        logBuff.append("\nUserProfile >>");
        logBuff.append("\nresultcode  : " + resultcode);
        logBuff.append("\nmessage  : " + message);
        logBuff.append("\n" + response.toString());
        logBuff.append("\nUserProfile <<");

        return logBuff.toString();
    }
}
