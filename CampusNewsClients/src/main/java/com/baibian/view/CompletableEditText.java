package com.baibian.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.baibian.R;
import com.baibian.tool.ToastTools;
import com.baibian.tool.UI_Tools;

/**
 * Created by Ellly on 2017/8/14.
 */

public class CompletableEditText extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher{
    private boolean hasFocus;
    private Drawable mDrawable;
    private OnCancelFocusListener mListener;

    public interface OnCancelFocusListener{
        void cancelFocus(EditText editText);
    }
    public void setOnCancelFocusListener(OnCancelFocusListener listener){
        mListener = listener;
    }
    public CompletableEditText(Context context) {
        super(context);
        init();
    }

    public CompletableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompletableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFocus){
            setDrawableVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (getCompoundDrawables()[2] != null){
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height)/2;
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y <(distance + height);
                if (isInnerWidth && isInnerHeight){
                    if (mListener != null){
                        mListener.cancelFocus(this);
                    }else {
                        ToastTools.ToastShow("No Listener Registered");
                    }
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus){
            setDrawableVisible(getText().length() > 0);
        }else {
            setDrawableVisible(false);
        }
    }
    private void init(){
        mDrawable = getCompoundDrawables()[2];
        if (mDrawable == null){
            mDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_favor);
        }
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        setDrawableVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }
    private void setDrawableVisible(Boolean isVisible){
        Drawable right = isVisible ? mDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }
}
