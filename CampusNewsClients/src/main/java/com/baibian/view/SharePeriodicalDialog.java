package com.baibian.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.tool.ToastTools;

/**
 * Created by Ellly on 2017/8/21.
 */

public class SharePeriodicalDialog extends Dialog implements View.OnClickListener{
    private TextView mPeriodicalTitle;
    private ImageView mPeriodicalCover;
    private ImageView mShareToQQ;
    private ImageView mCloseButton;

    public SharePeriodicalDialog(@NonNull Context context) {
        super(context);
    }

    public SharePeriodicalDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected SharePeriodicalDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_periodical_dialog_layout);
        setCanceledOnTouchOutside(true);
        initView();
    }

    private void initView() {
        mShareToQQ = (ImageView) findViewById(R.id.share_qq);
        mPeriodicalCover = (ImageView) findViewById(R.id.periodical_image_cover);
        mPeriodicalTitle = (TextView) findViewById(R.id.periodical_title);
        mCloseButton = (ImageView) findViewById(R.id.close_button);

        mShareToQQ.setOnClickListener(this);
        mPeriodicalCover.setOnClickListener(this);
        mPeriodicalTitle.setOnClickListener(this);
        mCloseButton.setOnClickListener(this);
    }

    public void setPeriodicalTitle(String title){
        mPeriodicalTitle.setText(title);
    }
    public void setPeriodicalCover(Drawable drawable){
        mPeriodicalCover.setImageDrawable(drawable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_button:
                dismiss();
                break;
            case R.id.share_qq:
                ToastTools.ToastShow("Sharing");
                break;
        }
    }
}
