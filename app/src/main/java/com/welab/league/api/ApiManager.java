package com.welab.league.api;

import com.welab.league.api.weblab.response.MemberInfo;
import com.welab.league.api.weblab.response.ResTabHome;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static class Singleton {
        private final static ApiManager INSTANCE = new ApiManager();
    }

    private ApiManager() {}

    public static ApiManager getInstance() {
        return Singleton.INSTANCE;
    }

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



    public void getMemberInfo(Callback<MemberInfo> callback) {
        getWelabService().getMemberInfo().enqueue(callback);
    }

    public void getTabHome(String userId, Callback<ResTabHome> callback) {
        getWelabService().getTabHomeInfo("league_main", userId).enqueue(callback);
    }

    private WelabService getWelabService() {
        return new Retrofit.Builder()
                .baseUrl(WelabService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WelabService.class);
    }
}

