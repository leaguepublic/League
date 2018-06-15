package com.welab.league.manager.login;

import android.content.Intent;
import android.view.View;

abstract class Manager {
    protected View mLoginView;

    public abstract void logOut();
    public abstract void needLoginActivity(int requestCode, int resultCode, Intent data);
    public abstract boolean isLogin();
    public abstract void destroy();
    public abstract void login();

    public final void init(View loginView) {
        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
}
