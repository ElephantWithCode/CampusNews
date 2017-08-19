package com.baibian.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Ellly on 2017/8/14.
 */

public class ListenerScrollView extends ScrollView {
    private static final int ZOOM_SIZE = 15;
    private static final int MAX_SCROLL_LOAD = 100;
    private final String TAG = "listener_scroll_view";
    private OnScrollDownListener mListener;

    private View mInner;
    private Rect mNormalLayout = new Rect();
    private Rect mNewLayout = new Rect();
    private Handler mHandler = new Handler();
    private float previousY = 0;
    private float presentY = 0;
    private float coefficient = 0;
    private float resultCoe = 0;
    public ListenerScrollView(Context context) {
        super(context);
    }

    public ListenerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            mInner = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                previousY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isNeedMove()){
                    if (mNormalLayout.isEmpty()){
                        mNormalLayout.set(mInner.getLeft(), mInner.getTop(), mInner.getRight(), mInner.getBottom());
                    }
                    float nowY = ev.getY();
/**
 *  function : 1/(x+1)
 */
                    coefficient += 0.15;
                    resultCoe = 1/(coefficient + 1);
//                    int offsetY = (int) ((previousY - nowY)/ ZOOM_SIZE);
/**
 * I need a better function to get suitable value for offsetY
 * the present one is Ln()
 */
//                    int offsetY = (int) Math.log(previousY - nowY + 1);
                    int offsetY = (int) ((previousY - nowY) * resultCoe / ZOOM_SIZE);

                    Log.d(TAG+"asd", offsetY + "    " + coefficient + "    " + resultCoe);
                    if (mInner != null){
                        mInner.layout(mInner.getLeft(), mInner.getTop() - offsetY, mInner.getRight(), mInner.getBottom() - offsetY);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                resultCoe = coefficient = 0;
                presentY = ev.getY();
                if (!mNormalLayout.isEmpty()){
                    Log.d(TAG + "1", mInner.getTop() - mNormalLayout.top + "");
                    animateBackTo(mNormalLayout);
                    /*if (mListener != null){
                        mNewLayout = mListener.onDropReleased(mNormalLayout.top - mInner.getTop(), mInner);
                    }*/
                }
                if (mListener != null){
                    mListener.onScrollDownComplete(presentY - previousY);
                    Log.d(TAG, presentY + " " + previousY + "   " + presentY + previousY + "");
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void animateBackTo(final Rect rectToAnimateTo) {
/*        TranslateAnimation ta = new TranslateAnimation(0, 0, mInner.getTop(), rectToAnimateTo.top-100);
        ta.setDuration(200);
        mInner.startAnimation(ta);
        mInner.layout(rectToAnimateTo.left, rectToAnimateTo.top-100, rectToAnimateTo.right, rectToAnimateTo.bottom-100);
        rectToAnimateTo.setEmpty();
        int offset = rectToAnimateTo.top - mInner.getTop();*/
        ValueAnimator va = ValueAnimator.ofInt(mInner.getTop(), rectToAnimateTo.top);
        final int previousTop = mInner.getTop();
        va.setDuration(200).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int offset = (int) animation.getAnimatedValue();
                if (isSmallDistance(previousTop) || Math.abs(offset) > 100) {
                    mInner.layout(mInner.getLeft(), offset, mInner.getRight(), mInner.getMeasuredHeight() + offset);
                }
                Log.d(TAG +"abc", mInner.getTop() + "  " + offset + "  " +previousTop);
            }
        });
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mListener != null && !isSmallDistance(previousTop)){
                    mListener.onDropReleased(mNormalLayout.top - mInner.getTop(), mInner);
                }
                mNewLayout.set(mInner.getLeft(), mInner.getTop(), mInner.getRight(), mInner.getBottom());
            }
        });
        va.start();
        rectToAnimateTo.setEmpty();
    }

    private boolean isSmallDistance(int previousTop) {
        return Math.abs(previousTop) < MAX_SCROLL_LOAD;
    }

    public void setOnScrollDownListener(OnScrollDownListener listener){
        mListener = listener;
    }
    public interface OnScrollDownListener{
        void onScrollDownComplete(float offsetY);
        Rect onDropReleased(int offsetY, View inner);
    }

    public boolean isNeedMove() {
        int offset = mInner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        return scrollY == offset;
    }
}
