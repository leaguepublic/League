package com.welab.league.manager.login;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

class FacebookManager extends Manager {
    private static FacebookManager mInstance;

    private CallbackManager mCallbackManager;
    private Activity mActivity;

    private FacebookManager(Activity activity) {
        mActivity = activity;

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.e("TAG", "LJS== access Token : " + loginResult.getAccessToken());
                        Profile profile = Profile.getCurrentProfile();

                        Log.e("TAG", "LJS== profile : " + profile);

                        if (profile != null) {
                            Log.e("TAG", "LJS== name : " + profile.getName());
                            Log.e("TAG", "LJS== id : " + profile.getId());
                        }
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    public synchronized static FacebookManager getInstance(Activity activity) {
        if (mInstance == null) {
            mInstance = new FacebookManager(activity);
        }

        return mInstance;
    }

//    @Override
//    public void init(View loginView) {
//
//        mCallbackManager = CallbackManager.Factory.create();
//
//        // Callback registration
//        ((LoginButton) loginView).registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                Log.e("TAG", "LJS== access Token : " + loginResult.getAccessToken());
//                Profile profile = Profile.getCurrentProfile();
//
//                Log.e("TAG", "LJS== profile : " + profile);
//
//                if (profile != null) {
//                    Log.e("TAG", "LJS== name : " + profile.getName());
//                    Log.e("TAG", "LJS== id : " + profile.getId());
//                }
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });
//    }

    @Override
    public void logOut() {
        LoginManager.getInstance().logOut();
    }

    @Override
    public void needLoginActivity(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean isLogin() {
        return Profile.getCurrentProfile() != null;
    }

    @Override
    public void destroy() {}

    @Override
    public void login() {
        LoginManager.getInstance().logInWithReadPermissions(mActivity, Arrays.asList("public_profile"));
    }
}
