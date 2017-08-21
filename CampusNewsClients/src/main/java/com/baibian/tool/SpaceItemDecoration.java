package com.baibian.tool;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ellly on 2017/7/29.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private boolean isHorizontal;
    public SpaceItemDecoration(int space, boolean isHorizontal){
        this.space = space;
        this.isHorizontal = isHorizontal;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (isHorizontal) {
            if (parent.getChildPosition(view) != 0){
                outRect.left = space;
            }
        }else {
            outRect.top = space;
        }
    }
}
