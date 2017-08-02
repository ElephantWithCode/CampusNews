package com.baibian.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.baibian.R;

public class OtherInformationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout otherInformationLayout;
    private NestedScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_information);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        otherInformationLayout = (RelativeLayout) findViewById(R.id.other_information_layout);
        scrollView = (NestedScrollView) findViewById(R.id.scroll_view);


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
}
