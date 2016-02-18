package com.example.ammach.formproject;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class LauncherActivity extends AppCompatActivity {

    ImageView imageView;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        initviews();

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.custom_animation);
        imageView.setAnimation(animation);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LauncherActivity.this,AddActivity.class));
                finish();
            }
        },4300);
    }

    ///////////////////////////////////////////////////////////////////////////
    // initiate views
    ///////////////////////////////////////////////////////////////////////////
    private void initviews() {
        imageView= (ImageView) findViewById(R.id.imageView);
    }
}
