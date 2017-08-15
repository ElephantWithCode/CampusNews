package com.baibian.view;

import android.content.Context;
import android.graphics.Rect;
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
    private static final int ZOOM_SIZE = 10;
    private final String TAG = "listener_scroll_view";
    private OnScrollDownListener mListener;

    private View mInner;
    private Rect mNormalLayout = new Rect();
    private float previousY = 0;
    private float presentY = 0;
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
//                    int offsetY = (int) ((previousY - nowY)/ ZOOM_SIZE);
/**
 * I need a better function to get suitable value for offsetY
 * the present one is Ln()
 */
                    int offsetY = (int) Math.log(previousY - nowY + 1);
                    Log.d(TAG, offsetY + "");
                    if (mInner != null){
                        mInner.layout(mInner.getLeft(), mInner.getTop() - offsetY, mInner.getRight(), mInner.getBottom() - offsetY);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                presentY = ev.getY();
                if (!mNormalLayout.isEmpty()){
                    Log.d(TAG + "1", mInner.getTop() - mNormalLayout.top + "");
                    if (mListener != null){
                        mListener.onDropReleased(mNormalLayout.top - mInner.getTop());
                    }
                    animateBack();
                }
                if (mListener != null){
                    mListener.onScrollDownComplete(presentY - previousY);
                    Log.d(TAG, presentY + " " + previousY + "   " + presentY + previousY + "");
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void animateBack() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, mInner.getTop(), mNormalLayout.top);
        ta.setDuration(200);
        mInner.startAnimation(ta);
        mInner.layout(mNormalLayout.left, mNormalLayout.top, mNormalLayout.right, mNormalLayout.bottom);
        mNormalLayout.setEmpty();
    }

    public void setOnScrollDownListener(OnScrollDownListener listener){
        mListener = listener;
    }
    public interface OnScrollDownListener{
        void onScrollDownComplete(float offsetY);
        void onDropReleased(int offsetY);
    }

    public boolean isNeedMove() {
        int offset = mInner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        return scrollY == 0 || scrollY == offset;
    }
}
