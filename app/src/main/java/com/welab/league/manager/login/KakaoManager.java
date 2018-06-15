package com.welab.league.manager.login;

import android.content.Intent;
import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.ApiErrorCode;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

class KakaoManager extends Manager {
    private static KakaoManager mInstance;

    private SessionCallback mCallback;

    private KakaoManager() {
        mCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(mCallback);
    }

    public synchronized static KakaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new KakaoManager();
        }

        return mInstance;
    }

//    @Override
//    public void init(View loginView) {
//        mLoginView = loginView;
//
//        mCallback = new SessionCallback();
//        Session.getCurrentSession().addCallback(mCallback);
//    }

    @Override
    public boolean isLogin() {
        // true일 경우 SessionCallback이 자동 호출 아닐경우 아무것도 안함.
        return Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    public void needLoginActivity(int requestCode, int resultCode, Intent data) {
        Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void logOut() {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                // 여기서 UI관련 동작은 정상 동작 안할 수 있다.
                mLoginView.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    @Override
    public void destroy() {
        Session.getCurrentSession().removeCallback(mCallback);
    }

    @Override
    public void login() {
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    private void requestMe() {
        UserManagement.getInstance().requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;

                // 정보를 못가져 오면 어떻하지? 다시 로그인 시킬까??

                int result = errorResult.getErrorCode();
                if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                    Log.e("TAG", "LJS== result : " + result);
                } else {
                    // 어쩃든 에러
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e("TAG", "LJS== onSessionClosed ==");
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.e("TAG", "LJS== userProfile : " + userProfile.toString());
            }

            @Override
            public void onNotSignedUp() {
                Log.e("TAG", "LJS== onNotSignedUp ==");
            }
        });
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Log.e("TAG", "LJS== Session Open ==");

            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Log.e("TAG", "LJS== onSessionOpenFailedn : " + exception.toString());
            }
        }
    }
}
