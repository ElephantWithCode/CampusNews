package com.baibian.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.app.AppApplication;
import com.baibian.bean.RecyclerItemContent;
import com.baibian.tool.RecyclerViewCommonTool.CommonAdapter;
import com.baibian.tool.RecyclerViewCommonTool.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager llm;
    private List<RecyclerItemContent.RecyclerItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (((AppApplication) getApplication()).isNightMode()){
            setTheme(R.style.NightTheme);
        }else {
            setTheme(R.style.DayTheme);
        }
        setContentView(R.layout.activity_recycler);
        getDataFromWeb();
        initRecycler();
    }

    private void getDataFromWeb() {
        //TODO with mData
        mData = RecyclerItemContent.mData;
    }

    private void initRecycler() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_recycler_view);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_item_decoration_drawable));
        mRecyclerView.addItemDecoration(itemDecoration);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(new CommonAdapter<RecyclerItemContent.RecyclerItem>(this, R.layout.recycler_item_layout, mData){

            int position;
            ImageView mUndoButton;
            TextView mContent;
            TextView mTitle;
            BGABadgeImageView mBadgeImageView;
            @Override
            public void convert(final ViewHolder holder, RecyclerItemContent.RecyclerItem recyclerItem) {
                mTitle = (TextView) holder.getItemView().findViewById(R.id.recycler_title);
                mContent = (TextView) holder.getItemView().findViewById(R.id.recycler_content);
                mBadgeImageView = (BGABadgeImageView) holder.getItemView().findViewById(R.id.recycler_notification_image);
                mUndoButton = (ImageView) holder.getItemView().findViewById(R.id.recycler_back_direction);

                mBadgeImageView.showCirclePointBadge();
                mUndoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position = holder.getLayoutPosition();
                        mDatas.remove(position);
                        notifyItemRemoved(position);
                        sendMessageToBack();
                    }
                });
            }
        });
    }

    private void sendMessageToBack() {
        //TODO
    }
}
