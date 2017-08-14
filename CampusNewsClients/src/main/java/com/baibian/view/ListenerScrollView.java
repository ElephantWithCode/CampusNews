package com.baibian.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Ellly on 2017/8/14.
 */

public class ListenerScrollView extends ScrollView {
    private final String TAG = "listener_scroll_view";
    private OnScrollDownListener mListener;

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
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                previousY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                presentY = ev.getY();
                if (mListener != null){
                    mListener.onScrollDownComplete(presentY - previousY);
                    Log.d(TAG, presentY + " " + previousY + "   " + presentY + previousY + "");
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setOnScrollDownListener(OnScrollDownListener listener){
        mListener = listener;
    }
    public interface OnScrollDownListener{
        void onScrollDownComplete(float offsetY);
    }
}
