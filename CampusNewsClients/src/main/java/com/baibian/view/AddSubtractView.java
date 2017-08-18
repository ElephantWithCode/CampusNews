package com.baibian.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.baibian.R;
import com.baibian.crawler.StreamTool;
import com.baibian.tool.UI_Tools;

/**
 * Created by Ellly on 2017/8/18.
 */

public class AddSubtractView extends RelativeLayout implements View.OnClickListener, TextWatcher{

    private Button mAddButton;
    private Button mSubtractButton;
    private EditText mFigureEditText;
    private int mCount = 1;
    private int MAX_COUNT = 20;
    private OnCountChangeListener mListener;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()){
            mFigureEditText.setText(String.valueOf(1));
            mFigureEditText.setSelection(1);
            return;
        }
        mCount = Integer.valueOf(s.toString());
        if (mCount > MAX_COUNT){
            mFigureEditText.setText(String.valueOf(MAX_COUNT));
            mFigureEditText.setSelection(String.valueOf(MAX_COUNT).length());
        }
        if (mListener != null){
            mListener.onCountChange(mCount);
        }
    }

    public interface OnCountChangeListener{
        void onCountChange(int count);
    }
    public AddSubtractView(Context context) {
        super(context);
        initView();
    }


    public AddSubtractView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AddSubtractView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setOnCountChangeListener(OnCountChangeListener listener){
        mListener = listener;
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.add_subtract_view, this);
        mAddButton = (Button) findViewById(R.id.add_btn);
        mSubtractButton = (Button) findViewById(R.id.subtract_btn);
        mFigureEditText = (EditText) findViewById(R.id.figure_view);
        mAddButton.setOnClickListener(this);
        mSubtractButton.setOnClickListener(this);
        mFigureEditText.addTextChangedListener(this);
        mFigureEditText.setSelection(mFigureEditText.getText().toString().length());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                ++mCount;
                if (mCount <= MAX_COUNT) {
                    changeCount(mCount);
                }
                mFigureEditText.clearFocus();
                break;
            case R.id.subtract_btn:
                if (mCount > 0) {
                    --mCount;
                    changeCount(mCount);
                }
                mFigureEditText.clearFocus();
                break;
        }
    }

    public void changeCount(int count) {
        String content = String.valueOf(count);
        if (count <= MAX_COUNT && count > 0) {
            mFigureEditText.setText(content);
            mFigureEditText.setSelection(content.length());
            if (mListener != null){
                mListener.onCountChange(count);
            }
        }
    }
}
