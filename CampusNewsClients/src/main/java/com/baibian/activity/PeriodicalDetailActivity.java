package com.baibian.activity;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.baibian.R;
import com.baibian.tool.MeasureTools;
import com.baibian.tool.ToastTools;
import com.baibian.view.CommentCardView;
import com.baibian.view.ListenerScrollView;

import java.util.ArrayList;
import java.util.List;

public class PeriodicalDetailActivity extends AppCompatActivity {

    private static final String TAG = "periodical_detail_activity";
    private ListenerScrollView mScrollView;
    private RelativeLayout mBottomNavBar;
    private LinearLayout mCommentFatherLayout;
    private int mBottomHeight = 0;
    private int mBottomOriginHeight = 0;
    private List<CommentCardView.CardContent> mCardDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodical_detail);


        mScrollView = (ListenerScrollView) findViewById(R.id.scroll_view);
        mBottomNavBar = (RelativeLayout) findViewById(R.id.bottom_nav_bar);
        mCommentFatherLayout = (LinearLayout) findViewById(R.id.comment_father_view_layout);

        mBottomOriginHeight = mBottomHeight = MeasureTools.measureWidthAndHeight(mBottomNavBar)[1];


        mScrollView.setOnScrollDownListener(new ListenerScrollView.OnScrollDownListener() {
            @Override
            public void onScrollDownComplete(float scrollY) {
                if (scrollY > 0){
                    Log.d("www111", mBottomHeight + "");
                    if (mBottomHeight == 0) {
                        startNavAnimation(0, mBottomOriginHeight);
                    }
                }else if (scrollY < 0){
                    Log.d("hhh111", mBottomHeight + "");
                    if (mBottomHeight == mBottomOriginHeight) {
                        startNavAnimation(mBottomHeight, 0);
                    }
                }
            }

            @Override
            public void onDropReleased(int offsetY) {
                mCommentFatherLayout.addView(new Button(PeriodicalDetailActivity.this));
                ToastTools.ToastShow("HHHHHHHHH");
            }
        });

        initCommentContent();

    }

    private void initCommentContent() {
        for (int i = 0; i < 10; i++){
            mCommentFatherLayout.addView(new CommentCardView(this));
        }
    }

    private void startNavAnimation(int startY, int endY) {
        ValueAnimator animator = ValueAnimator.ofInt(startY, endY);
        animator.setDuration(200);
//        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBottomHeight = mBottomNavBar.getLayoutParams().height = (int) animation.getAnimatedValue();
                Log.d("fff111", mBottomHeight + "");

                mBottomNavBar.requestLayout();
            }
        });
        animator.start();
    }
}
