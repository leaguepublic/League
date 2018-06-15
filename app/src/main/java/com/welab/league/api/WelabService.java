package com.welab.league.api;

import com.welab.league.api.response.weblab.MemberInfo;

import retrofit2.Call;

public interface WelabService {
    Call<MemberInfo> getMemberInfo();
}
