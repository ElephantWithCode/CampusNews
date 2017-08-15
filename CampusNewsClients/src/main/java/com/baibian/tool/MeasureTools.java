package com.baibian.tool;

import android.view.View;

/**
 * Created by Ellly on 2017/8/15.
 */

public class MeasureTools {
    private MeasureTools(){

    }
    public static int[] measureWidthAndHeight(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int[] datas = new int[2];
        datas[0] = view.getMeasuredWidth();
        datas[1] = view.getMeasuredHeight();
        return datas;
    }
}
