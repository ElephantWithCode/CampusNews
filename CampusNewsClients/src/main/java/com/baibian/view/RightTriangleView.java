package com.baibian.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ellly on 2017/8/25.
 */

public class RightTriangleView extends View {

    private int measureWidth = 0;
    private int measureHeight = 0;
    private int mPaintColor = Color.parseColor("#47bafe");
    private Path mPath;
    private Paint mPaint;

    public RightTriangleView(Context context) {
        super(context);
    }

    public RightTriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureWidth = getMeasuredWidth();
        measureHeight = getMeasuredHeight();
    }

    public RightTriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        mPaint.setColor(mPaintColor);
        mPath.moveTo(0, 0);
        mPath.lineTo(measureWidth, measureHeight);
        mPath.lineTo(0, measureHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
