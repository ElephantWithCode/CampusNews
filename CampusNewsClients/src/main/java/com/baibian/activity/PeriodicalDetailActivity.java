package com.baibian.activity;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.baibian.R;
import com.baibian.view.ListenerScrollView;

public class PeriodicalDetailActivity extends AppCompatActivity {

    private ListenerScrollView mScrollView;
    private RelativeLayout mBottomNavBar;
    private int mBottomHeight = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodical_detail);

        mScrollView = (ListenerScrollView) findViewById(R.id.scroll_view);
        mBottomNavBar = (RelativeLayout) findViewById(R.id.bottom_nav_bar);

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        mBottomNavBar.measure(w, h);
        mBottomHeight = mBottomNavBar.getMeasuredHeight();
        Log.d("111", mBottomHeight + "");

        mScrollView.setOnScrollDownListener(new ListenerScrollView.OnScrollDownListener() {
            @Override
            public void onScrollDownComplete(float scrollY) {
                if (scrollY > 0){
                    startNavAnimation(0, mBottomHeight);
                }else {
                    startNavAnimation(mBottomHeight, 0);
                }
            }
        });

    }

    private void startNavAnimation(int startY, int endY) {
        ValueAnimator animator = ValueAnimator.ofInt(startY, endY);
        animator.setDuration(200);
//        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBottomNavBar.getLayoutParams().height = (int) animation.getAnimatedValue();
                mBottomNavBar.requestLayout();
            }
        });
        animator.start();
    }
}
