package com.baibian.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.tool.ToastTools;
import com.baibian.view.RevealFollowButton;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OtherInformationActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{

    private static final String IS_FOCUSED = "is_focused";
    private Toolbar toolbar;
    private ImageView collapsingImageView;
    private CircleImageView userPortrait;

    private TextView hisTopic;
    private TextView hisPoint;
    private TextView hisPresentation;
    private TextView nickName;
    private TextView personalSignature;
    private TextView likeAmounts;
    private TextView focusAmounts;
    private TextView focusedAmounts;

    private TextView genderAgeConstellation;
    private TextView collegeName;
    private TextView provinceCity;
    private LinearLayout honorLayout;
    private List<String> honors;
    private TextView level;

    private String backgroundImageUrl;
    private String userPortraitImageUrl;

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private RevealFollowButton followButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_information);

        initViews();
        initUrls();
        initData();
        Glide.with(OtherInformationActivity.this).load(backgroundImageUrl).crossFade().into(collapsingImageView);
        Glide.with(OtherInformationActivity.this).load(userPortraitImageUrl).crossFade().into(userPortrait);
        initListeners();
        initToolbar();
    }

    private void initListeners() {
        hisTopic.setOnClickListener(this);
        hisPoint.setOnClickListener(this);
        hisPresentation.setOnClickListener(this);
        userPortrait.setOnClickListener(this);
        collapsingImageView.setOnClickListener(this);
    }

    private void initData() {

        /**
         *  TODO Honors to be initialized.
         *  TODO In what way should the data be initialized remains unknown.
         *  Here Exampled with SharePreferences.
         */

        editor = sharedPreferences.edit();
        followButton.setFollowed(sharedPreferences.getBoolean(IS_FOCUSED, true), false);
        followButton.setOnFollowedClickListener(new RevealFollowButton.OnFollowedClickListener() {
            @Override
            public void onFollowedClick(boolean isFollowed) {
                editor.putBoolean(IS_FOCUSED, isFollowed);
                editor.apply();
                ToastTools.ToastShow("Followed:" + isFollowed);
            }
        });
    }

    private void initViews() {


        sharedPreferences = getSharedPreferences("other_information_activity", MODE_PRIVATE);


        followButton = (RevealFollowButton) findViewById(R.id.focus_action);
        nickName = (TextView) findViewById(R.id.other_user_name);
        personalSignature = (TextView) findViewById(R.id.other_personal_signal);
        likeAmounts = (TextView) findViewById(R.id.like_amount);
        focusAmounts = (TextView) findViewById(R.id.focus_amount);
        focusedAmounts = (TextView) findViewById(R.id.focused_account);
        genderAgeConstellation = (TextView) findViewById(R.id.gender_age_constellation);
        collegeName = (TextView) findViewById(R.id.college_name);
        provinceCity = (TextView) findViewById(R.id.province_city);
        honorLayout = (LinearLayout) findViewById(R.id.user_honor_layout);
        level = (TextView) findViewById(R.id.user_level);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        hisTopic = (TextView) findViewById(R.id.his_topic);
        hisPoint = (TextView) findViewById(R.id.his_point);
        hisPresentation = (TextView) findViewById(R.id.his_presentation);
        collapsingImageView = (ImageView) findViewById(R.id.other_information_background);
        userPortrait = (CircleImageView) findViewById(R.id.other_user_portrait);
    }

    private void initUrls() {
        userPortraitImageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502202777406&di=b3d9f0c393af81ec70e84c4e43e276b6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D32c015eaf6039245b5b8e94ceffdceb7%2Fd788d43f8794a4c28c10040c04f41bd5ad6e39e2.jpg";
        backgroundImageUrl = "http://pic39.nipic.com/20140312/18085061_092729513131_2.jpg";
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

            case R.id.other_user_portrait:
                Intent intent9 = new Intent(OtherInformationActivity.this, FullscreenScoopActivity.class);
                intent9.putExtra("user_portrait_url", userPortraitImageUrl);
                startActivity(intent9);
                break;
            case R.id.other_information_background:
                Intent intent10 = new Intent(OtherInformationActivity.this, FullscreenScoopActivity.class);
                intent10.putExtra("user_portrait_url", backgroundImageUrl);
                startActivity(intent10);
                break;
            case R.id.his_topic:
                Intent intent = new Intent(OtherInformationActivity.this, HisFocusActivity.class);
                startActivity(intent);
                break;
            case R.id.his_point:
                Intent intent1 = new Intent(OtherInformationActivity.this, HisFansActivity.class);
                startActivity(intent1);
                break;
            case R.id.his_presentation:
                Intent intent2 = new Intent(OtherInformationActivity.this, FullscreenScoopActivity.class);
                startActivity(intent2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.other_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.user_private_message:
                ToastTools.ToastShow("Private Messages");
                break;
            case R.id.user_share:
                /**
                 * TODO WHAT TO SHARE
                 * The way of sharing could be referred in the project "re-music"
                 */

                ToastTools.ToastShow("Share");
                break;
        }
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
