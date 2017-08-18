package com.baibian.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.tool.RecyclerViewCommonTool.CommonAdapter;
import com.baibian.tool.RecyclerViewCommonTool.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PeriodicalCategoryActivity extends AppCompatActivity {


    static class CategoryChapter{
        private String mChapterTitle;
        private int mState;

        public int getmState() {
            return mState;
        }

        public void setmState(int mState) {
            this.mState = mState;
        }

        public String getmChapterTitle() {
            return mChapterTitle;
        }

        public void setmChapterTitle(String mChapterTitle) {
            this.mChapterTitle = mChapterTitle;
        }
    }
    private static final int ORIGIN_COUNT = 7;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private List<CategoryChapter> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodical_category);
        initVariousViews();
        initRecyclerRelations();
        initToolbar();
    }

    private void initVariousViews() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void initRecyclerRelations() {
        for (int i = 0; i < ORIGIN_COUNT; i++){
            mData.add(new CategoryChapter());
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration mDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_bg));
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(new CommonAdapter<CategoryChapter>(this, R.layout.periodical_category_item, mData){
            @Override
            public void convert(ViewHolder holder, CategoryChapter categoryChapter) {
                    TextView chapterTitle = (TextView)  holder.getItemView().findViewById(R.id.category_name);
                    TextView chapterPurchaseState = (TextView) holder.getItemView().findViewById(R.id.category_purchase_state);
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
