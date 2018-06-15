package com.welab.league.app;

import android.app.Application;

import com.kakao.auth.KakaoSDK;
import com.welab.league.adapter.KakaoSDKAdapter;

public class LeagueApplication extends Application {
    private static volatile LeagueApplication instance = null;

    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static LeagueApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());
    }
}
