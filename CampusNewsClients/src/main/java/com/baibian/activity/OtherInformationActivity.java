package com.baibian.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baibian.R;

public class OtherInformationActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TextView hisTopic;
    private TextView hisPoint;
    private TextView hisPresentation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_information);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        hisTopic = (TextView) findViewById(R.id.his_topic);
        hisPoint = (TextView) findViewById(R.id.his_point);
        hisPresentation = (TextView) findViewById(R.id.his_presentation);

        hisTopic.setOnClickListener(this);
        hisPoint.setOnClickListener(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.his_topic:
                Intent intent = new Intent(OtherInformationActivity.this, HisFocusActivity.class);
                startActivity(intent);
                break;
            case R.id.his_point:
                Intent intent1 = new Intent(OtherInformationActivity.this, HisFansActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
