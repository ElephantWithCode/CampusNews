package com.baibian.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.baibian.R;
import com.baibian.app.AppApplication;
import com.baibian.bean.BookShelfItemContent;
import com.baibian.tool.MyApplication;
import com.baibian.tool.RecyclerViewCommonTool.CommonAdapter;
import com.baibian.tool.RecyclerViewCommonTool.LoadableRecyclerView;
import com.baibian.tool.RecyclerViewCommonTool.ViewHolder;
import com.bumptech.glide.Glide;

public class MyBookshelfActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (((AppApplication) getApplication()).isNightMode()){
            setTheme(R.style.NightTheme);
        }else {
            setTheme(R.style.DayTheme);
        }
        setContentView(R.layout.activity_my_bookshelf);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.bookshelf_recycler);
        mRecyclerView.setAdapter(new CommonAdapter<BookShelfItemContent.BookShelf>(this, R.layout.bookshelf_item_layout, BookShelfItemContent.mData){
            @Override
            public void convert(ViewHolder holder, BookShelfItemContent.BookShelf bookShelf) {
                ImageView mBookCover = (ImageView) holder.getItemView().findViewById(R.id.periodical_image_cover);
//                Glide.with(this).load(bookShelf.getmImageUrl()).crossFade().into(mBookCover); //正式加载图片时使用的样例
            }
        });
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private int space;
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 30;
                /*if ((parent.getChildLayoutPosition(view) + 1) % 3 == 0 ){
                    outRect.right = 0;
                }else {
                    outRect.right = 30;
                }*/
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
