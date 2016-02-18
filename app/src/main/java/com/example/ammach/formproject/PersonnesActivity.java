package com.example.ammach.formproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import adapters.ViewPagerAdapter;
import utils.SlidingTabLayout;

public class PersonnesActivity extends AppCompatActivity {

    SlidingTabLayout slidingTabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnes);
        Toolbar toolbar=new Toolbar(this);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(PersonnesActivity.this,AddActivity.class));
                finish();
            }
        });

        initviews();
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);

    }

    ///////////////////////////////////////////////////////////////////////////
    // initiate views
    ///////////////////////////////////////////////////////////////////////////
    private void initviews() {
        slidingTabLayout= (SlidingTabLayout) findViewById(R.id.slidingTabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
    }


}
