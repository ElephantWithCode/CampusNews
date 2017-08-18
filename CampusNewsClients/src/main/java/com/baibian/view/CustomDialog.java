package com.baibian.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.tool.ToastTools;

/**
 * Created by Ellly on 2017/8/17.
 */

public class CustomDialog extends Dialog implements View.OnClickListener{
    private TextView mTitle;
    private TextView mPostiveBtn;
    private TextView mNegativeBtn;
    private EditText mEditText;
    private OnDialogDismissListener mListener;

    private String mPostiveName;
    private String mNegativeName;
    private String mTitleName;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_layout);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.comment_content);
        mTitle = (TextView) findViewById(R.id.dialog_title);
        mPostiveBtn = (TextView) findViewById(R.id.positive_btn);
        mNegativeBtn = (TextView) findViewById(R.id.negative_btn);
        mPostiveBtn.setOnClickListener(this);
        mNegativeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.positive_btn:
                if (mListener != null){
                    mListener.OnDismiss(mEditText.getText().toString(), true);
                }
                dismiss();
                break;
            case R.id.negative_btn:
                if (mListener != null){
                    mListener.OnDismiss(mEditText.getText().toString(), false);
                }
                dismiss();
                break;
        }
    }
    public CustomDialog setMyTitle(String titleName){
        mTitle.setText(titleName);
        return this;
    }
    public CustomDialog setPositiveName(String positiveName){
        mPostiveBtn.setText(positiveName);
        return this;
    }
    public CustomDialog setNegativeName(String negativeName){
        mNegativeBtn.setText(negativeName);
        return this;
    }
    public CustomDialog setOnPostiveButtonClicked(View.OnClickListener listener){
        mPostiveBtn.setOnClickListener(listener);
        return this;
    }
    public CustomDialog setOnNegativeButtonClicked(View.OnClickListener listener){
        mNegativeBtn.setOnClickListener(listener);
        return this;
    }
    public CustomDialog setOnDialogDismissListener(OnDialogDismissListener listener){
        mListener = listener;
        return this;
    }

    public interface OnDialogDismissListener{
        void OnDismiss(String editTextContent, boolean isPositive);
    }
}
