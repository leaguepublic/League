package com.welab.league.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.welab.league.R;

public class TeamInfoActivity extends Activity {

    public static void open(Context context) {
        context.startActivity(new Intent(context, TeamInfoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
    }
}
