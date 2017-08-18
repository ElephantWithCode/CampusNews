package com.baibian.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.baibian.R;
import com.baibian.tool.ToastTools;

/**
 * Created by Ellly on 2017/8/18.
 */

public abstract class CustomBottomUpDialog extends Dialog implements View.OnClickListener{
    private int mLayoutId;
    private Context mContext;
    private View mContentView;
    private Window mWindow;
    public CustomBottomUpDialog(@NonNull Context context, int layoutResId) {
        super(context, R.style.CustomDialogTheme);
        mContext = context;
        mLayoutId = layoutResId;
        mWindow = getWindow();
    }

    public CustomBottomUpDialog(@NonNull Context context, @StyleRes int themeResId, int layoutResId) {
        super(context, themeResId);
        mLayoutId = layoutResId;
    }

    protected CustomBottomUpDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        mContentView = LayoutInflater.from(getContext()).inflate(mLayoutId, null);
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(R.style.DialogAnimStyle);
        mWindow.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        android.view.WindowManager.LayoutParams lp = mWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        mWindow.setAttributes(lp);
        setContentView(mLayoutId);
        initView(this);
    }

    public abstract void initView(CustomBottomUpDialog dialog);
}
