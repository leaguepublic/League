package com.welab.league.manager.api;

import com.welab.league.api.WelabService;
import com.welab.league.api.response.weblab.MemberInfo;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private ApiManager() {}

//    public static Call<SearchResultByKeyword> getSearchResultByKeyword(String url, String keyword) {
//        KakaoMapService retrofitService = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(KakaoMapService.class);
//
//        return retrofitService.getSearchByKeyword(keyword);
//    }
//
//    public static void getSearchResultByKeywork(String url, String keyword, Callback<SearchResultByKeyword> callback) {
//        getSearchResultByKeyword(url, keyword).enqueue(callback);
//    }

    public static void getMemberInfo(String url, Callback<MemberInfo> callback) {
        WelabService welabService = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WelabService.class);

        welabService.getMemberInfo().enqueue(callback);
    }
}

