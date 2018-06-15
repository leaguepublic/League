package com.welab.league.api;

import com.welab.league.api.response.kakao.keyword.SearchResultByKeyword;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoMapService {
    public static final String Url = "https://dapi.kakao.com";

    // 카카오맵 개발자 REST Full API 주소
    // https://developers.kakao.com/docs/restapi/local

    @Headers("Authorization: KakaoAK fc66cb1816ec54647fbb84b069c80b09")
    @GET("/v2/local/search/keyword.json")
    Call<SearchResultByKeyword> getSearchByKeyword(@Query("query") String keyword);
}
