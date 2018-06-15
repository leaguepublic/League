package com.welab.league.manager.login;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.welab.league.api.response.weblab.MemberInfo;
import com.welab.league.manager.api.ApiManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {
    private static LoginManager mInstance;

    private KakaoManager mKakaoManager;
    private NaverManager mNaverManager;
    private FacebookManager mFacebookManager;

    private Manager mManager;

    private LoginManager(Activity activity) {
        mKakaoManager = KakaoManager.getInstance();
        mNaverManager = NaverManager.getInstance(activity);
        mFacebookManager = FacebookManager.getInstance(activity);
    }

    public static synchronized LoginManager getInstance(Activity activity) {
        if (mInstance == null) {
            mInstance = new LoginManager(activity);
        }

        return mInstance;
    }

    public void init(View kakaoButton, View naverButton, View facebookButton) {
        mKakaoManager.init(kakaoButton);
        mNaverManager.init(naverButton);
        mFacebookManager.init(facebookButton);

        isLogin();
    }

    public boolean isLogin() {
        if (mKakaoManager.isLogin() == true) {
            mManager = mKakaoManager;
        } else if (mNaverManager.isLogin() == true) {
            mManager = mNaverManager;
        } else if (mFacebookManager.isLogin() == true) {
            mManager = mFacebookManager;
        } else {
            mManager = null;
        }

        return mManager != null;
    }

    public void needLoginActivity(int requestCode, int resultCode, Intent data) {
        if (mManager != null) {
            mManager.needLoginActivity(requestCode, resultCode, data);
        }
    }

    public void destroy() {
        if (mManager != null) {
            mManager.destroy();
        }
    }

    public void checkMember() {
        ApiManager.getMemberInfo("", new Callback<MemberInfo>() {
            @Override
            public void onResponse(Call<MemberInfo> call, Response<MemberInfo> response) {

            }

            @Override
            public void onFailure(Call<MemberInfo> call, Throwable t) {

            }
        });
    }
}
