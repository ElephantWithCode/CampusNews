package com.baibian.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.baibian.R;
import com.baibian.bean.HisFansContent;
import com.baibian.bean.HisFansContent.HisFans;
import com.baibian.tool.RecyclerViewCommonTool.CommonAdapter;
import com.baibian.tool.RecyclerViewCommonTool.ViewHolder;

public class HisFansActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_fans);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        initRecyclerView();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("His Fans");
        }
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new CommonAdapter<HisFans>(this, R.layout.fans_information_layout, HisFansContent.FANSES){
            @Override
            public void convert(ViewHolder holder, HisFans fans) {

            }
        });
    }
}
