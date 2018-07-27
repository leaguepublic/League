package com.welab.league.manager.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.welab.league.R;
import com.welab.league.api.naver.UserProfile;

class NaverManager extends Manager {
    private static NaverManager mInstance;

    private OAuthLogin mOAuthLoginModule;

    private Activity mActivity;

    private NaverManager(Activity activity) {
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                activity
                ,activity.getString(R.string.naver_client_id)
                ,activity.getString(R.string.naver_client_secret)
                ,activity.getString(R.string.app_name)
        );

        mActivity = activity;
    }

    public synchronized static NaverManager getInstance(Activity activity) {
        if (mInstance == null) {
            mInstance = new NaverManager(activity);
        }

        return mInstance;
    }

//    @Override
//    public void init(View loginView) {
//        ((OAuthLoginButton) loginView).setOAuthLoginHandler(new OAuthLoginHandler() {
//                    @Override
//                    public void run(boolean success) {
//
//                        if (success) {
//                            String accessToken = mOAuthLoginModule.getAccessToken(mContext);
//                            String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
//                            long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
//                            String tokenType = mOAuthLoginModule.getTokenType(mContext);
//
//                            getUserProfile(accessToken);
//                        } else {
//                            String errorCode = mOAuthLoginModule.getLastErrorCode(mContext).getCode();
//                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
//                        }
//                    }
//                });
//    }

    @Override
    public void logOut() {
        mOAuthLoginModule.logout(mActivity);
    }

    @Override
    public void needLoginActivity(int requestCode, int resultCode, Intent data) {}

    @Override
    public boolean isLogin() {
        return TextUtils.isEmpty(mOAuthLoginModule.getAccessToken(mActivity)) == false;
    }

    @Override
    public void destroy() {}

    @Override
    public void login() {
        mOAuthLoginModule.startOauthLoginActivity(mActivity, new OAuthLoginHandler() {
                    @Override
                    public void run(boolean success) {

                        if (success) {
                            String accessToken = mOAuthLoginModule.getAccessToken(mActivity);
                            String refreshToken = mOAuthLoginModule.getRefreshToken(mActivity);
                            long expiresAt = mOAuthLoginModule.getExpiresAt(mActivity);
                            String tokenType = mOAuthLoginModule.getTokenType(mActivity);

                            getUserProfile(accessToken);
                        } else {
                            String errorCode = mOAuthLoginModule.getLastErrorCode(mActivity).getCode();
                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(mActivity);
                        }
                    }
                });
    }

    private void getUserProfile(final String toeken) {
        AsyncTask getProfileAsyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                String result = mOAuthLoginModule.requestApi(mActivity, toeken, "https://openapi.naver.com/v1/nid/me");
                Gson gson = new Gson();
                UserProfile userProfile = gson.fromJson(result, UserProfile.class);

                Log.e("TAG", "LJS== userProfile : " + userProfile.toString());
                return null;
            }
        };

        getProfileAsyncTask.execute();
    }
}
