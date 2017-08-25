package com.baibian.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baibian.R;
import com.baibian.bean.MyCollectionItemContent;
import com.baibian.tool.RecyclerViewCommonTool.CommonAdapter;
import com.baibian.tool.RecyclerViewCommonTool.LoadableRecyclerView;
import com.baibian.tool.RecyclerViewCommonTool.ViewHolder;

public class MyCollectionActivity extends AppCompatActivity {

    private LoadableRecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        mRecyclerView = new LoadableRecyclerView(this) {
            @Override
            public void onBottomRefreshData(LoadableRecyclerView recyclerview) {

            }
        };
        /*mRecyclerView.setAdapter(new CommonAdapter<MyCollectionItemContent.MyCollectionItem>(this, ) {
            @Override
            public void convert(ViewHolder holder, MyCollectionItemContent.MyCollectionItem myCollectionItem) {

            }
        }){

        };*/
    }
}
