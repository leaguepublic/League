package com.welab.league.api;

import com.welab.league.api.weblab.response.MemberInfo;
import com.welab.league.api.weblab.response.ResTabHome;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WelabService {
    public static final String URL = "http://45.119.147.62:8080/league/";

    Call<MemberInfo> getMemberInfo();

    @GET("get_data")
    Call<ResTabHome> getTabHomeInfo(@Query("cmd") String cmd, @Query("user_id") String userId);
}
