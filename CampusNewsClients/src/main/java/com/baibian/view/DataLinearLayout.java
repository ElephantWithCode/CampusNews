package com.baibian.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/17.
 */

public class DataLinearLayout extends LinearLayout {

    public static class DataAdapter<T>{

        interface OnDataChangeListener{
            void onDataChange();
        }

        private List<T> mData;
        private OnDataChangeListener mDataChangeListener;

        public DataAdapter(List<T> data){
            mData = data;
        }
        private void notifyDataChanged(List<T> newData){
            mData = newData;
            mDataChangeListener.onDataChange();
        }
        private boolean isFirstContainsSecond(List<T> listBefore, List<T> listAfter){
            return getDifferent(listBefore, listAfter).isEmpty();
        }
        private List<T> getDifferent(List<T> listBefore, List<T> listAfter){
            List<T> diff = new ArrayList<>();
            for (T t : listAfter){
                if (! listBefore.contains(t)){
                    diff.add(t);
                }
            }
            return diff;
        }
    }
    public DataLinearLayout(Context context) {
        super(context);
        initView();
    }

    public DataLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DataLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

}
