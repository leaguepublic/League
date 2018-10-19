package com.welab.league.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.welab.league.R;

public class CreateTeamActivity extends AppCompatActivity {

    public static void open(Context context) {
        context.startActivity(new Intent(context, CreateTeamActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_team);
    }
}
