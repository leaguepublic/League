package com.welab.league.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.welab.league.R;
import com.welab.league.manager.login.LoginManager;

public class LoginActivity extends Activity {

    private LoginManager mLoginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginManager = LoginManager.getInstance(this);
        mLoginManager.init(findViewById(R.id.kakao_login_button), findViewById(R.id.naver_login_button), findViewById(R.id.facebook_login_button));

        if (mLoginManager.isLogin() == true) {
            // 서버에서 사용자 정보를 받아 메인화면으로 이동.
            // 없을 경우 사용자 정보 입력 화면으로 이동.
        }

        LeagueMainActivity.open(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLoginManager.destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mLoginManager.needLoginActivity(requestCode, resultCode, data);
    }
}
