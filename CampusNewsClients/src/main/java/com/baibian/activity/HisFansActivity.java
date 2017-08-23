package com.baibian.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.bean.HisFansContent;
import com.baibian.bean.HisFansContent.HisFans;
import com.baibian.tool.RecyclerViewCommonTool.CommonAdapter;
import com.baibian.tool.RecyclerViewCommonTool.LoadableRecyclerView;
import com.baibian.tool.RecyclerViewCommonTool.ViewHolder;
import com.baibian.tool.ToastTools;
import com.baibian.view.RevealFollowButton;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class HisFansActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private RelativeLayout mContentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_fans);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView = new LoadableRecyclerView(this) {
            @Override
            public void onBottomRefreshData(LoadableRecyclerView mSelf) {
                ToastTools.ToastShow("Added");
                HisFansContent.addFansItem(new HisFans());
                mSelf.getAdapter().notifyDataSetChanged();
            }
        };
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mContentView = (RelativeLayout) findViewById(R.id.content_layout);
        RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.addRule(RelativeLayout.BELOW, R.id.app_bar);
        mContentView.addView(mRecyclerView, mLayoutParams);
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
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_item_decoration_drawable));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(new CommonAdapter<HisFans>(this, R.layout.fans_information_layout, HisFansContent.FANSES){
            @Override
            public void convert(ViewHolder holder, HisFans fans) {

                RevealFollowButton mFollowButton = (RevealFollowButton) holder.getItemView().findViewById(R.id.reveal_follow_btn);
                CircleImageView mUserPortrait= (CircleImageView) holder.getItemView().findViewById(R.id.user_portrait);
                LinearLayout mHonorLayout = (LinearLayout) holder.getItemView().findViewById(R.id.user_honor_layout);
                TextView mUserName = (TextView) holder.getItemView().findViewById(R.id.user_name);
                TextView mUserPersonalSignature = (TextView) holder.getItemView().findViewById(R.id.user_personal_signal);
                TextView mUserLevel = (TextView) holder.getItemView().findViewById(R.id.user_level);


                Glide.with(HisFansActivity.this).load("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=30161d0030292df583cea456d4583615/e1fe9925bc315c609050b3c087b1cb13485477dc.jpg").crossFade().into(mUserPortrait);
                //TODO add honors onto mHonorLayout
                mFollowButton.setOnFollowedClickListener(new RevealFollowButton.OnFollowedClickListener() {
                    @Override
                    public void onFollowedClick(boolean isFollowed) {
                        if (isFollowed) {
                            ToastTools.ToastShow("Followed");
                        } else {
                            ToastTools.ToastShow("UnFollowed");
                        }
                    }
                });
            }
        });
    }
}
