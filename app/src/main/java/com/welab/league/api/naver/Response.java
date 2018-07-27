package com.welab.league.api.naver;

public class Response {
    public String id;
    public String nickname;
    public String email;
    public String name;

    @Override
    public String toString() {
        StringBuilder logBuff = new StringBuilder();
        logBuff.append("\nResponse >>");
        logBuff.append("\nnickname : " + nickname);
        logBuff.append("\nemail : " + email);
        logBuff.append("\nname : " + name);
        logBuff.append("\nResponse <<");

        return logBuff.toString();
    }
}
