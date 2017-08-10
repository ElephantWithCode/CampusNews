package com.baibian.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baibian.R;
import com.baibian.tool.ToastTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/9.
 */

public class InflatableLinearLayout extends LinearLayout {

    private static int MAX_COUNT = 3;
    private List<LinearLayout> mLinearLayouts;
    private EditText mEditInformation;
    private ImageView mAddDeleteBtn;
    private LayoutInflater mInflater;
    private List<String> mSavedResidences;
    private List<EditText> mEditTexts;


    public InflatableLinearLayout(Context context) {
        super(context);
        initView();
    }

    public InflatableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public InflatableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        mEditTexts = new ArrayList<>();
        mSavedResidences = new ArrayList<>();
        mLinearLayouts = new ArrayList<>();
        mInflater = LayoutInflater.from(getContext());
        LinearLayout mItemContent1 = createItemContent();

        addItemView(mItemContent1);
        /*mEditInformation = (EditText) mItemContent.findViewById(R.id.content);
        mAddDeleteBtn = (ImageView) mItemContent.findViewById(R.id.button);
        addItemView(mItemContent);
        addItemView(mItemContent);*/
    }

    private LinearLayout createItemContent(){

        LinearLayout contentLayout = (LinearLayout) mInflater.inflate(R.layout.add_information_layout, null);
        EditText editText = (EditText) contentLayout.findViewById(R.id.content);
        mEditTexts.add(editText);
        final ImageView imageView = (ImageView) contentLayout.findViewById(R.id.button);
        /**
         * Due to the order that "createItemContent" is beyond "list.add", here "MAX_COUNT - 1" exists.
         */
        if (mLinearLayouts.size() >= MAX_COUNT - 1){
            imageView.setImageResource(R.drawable.ic_personal_delete);
        }
        imageView.setOnClickListener(new OnClickListener() {
            private boolean isFirstClick = true;
            private int position = mLinearLayouts.size() - 1;

            @Override
            public void onClick(View v) {
                //private boolean isFirstClick = mLinearLayouts.size() < MAX_COUNT; invalid seemingly though

                isFirstClick = (mLinearLayouts.indexOf(v.getParent()) + 1) == mLinearLayouts.size() && mLinearLayouts.size() != MAX_COUNT;
                if (isFirstClick) {
                    addItemView(createItemContent());
                    imageView.setImageResource(R.drawable.ic_personal_delete);
                    isFirstClick = false;
                } else {
                    removeItemView((LinearLayout) imageView.getParent());
                }
            }
        });
        return contentLayout;
    }

    public void setData(List<String> data){
        mSavedResidences = data;
    }
    public void setMaxCount(int count){
        MAX_COUNT = count;
    }

    private void setButtonImage(){
        ImageView imageButton = (ImageView) mLinearLayouts.get(mLinearLayouts.size() - 1).getChildAt(1);
        if (mLinearLayouts.size() < MAX_COUNT) {
            imageButton.setImageResource(R.drawable.ic_personal_add);
        } else {
            imageButton.setImageResource(R.drawable.ic_personal_delete);
        }
    }

    public List<String> getSavedResidences(){
        for (LinearLayout tempLinearLayout: mLinearLayouts) {
            EditText tempEditText = (EditText)tempLinearLayout.getChildAt(0);
            mSavedResidences.add(tempEditText.getText().toString());
        }
        return mSavedResidences;
    }

    private void removeItemView(LinearLayout linearLayout){
//        ToastTools.ToastShow("Second    " + position);
        mLinearLayouts.remove(linearLayout);
        removeView(linearLayout);
        ImageView itemImageView = (ImageView) mLinearLayouts.get(mLinearLayouts.size() - 1).getChildAt(1);
        itemImageView.setImageResource(R.drawable.ic_personal_add);
    }

    private void addItemView(LinearLayout linearLayout){
        if (mLinearLayouts.size() <= MAX_COUNT - 1) {
            mLinearLayouts.add(linearLayout);
            addView(linearLayout);
            setButtonImage();
        }else {
            ToastTools.ToastShow("Already reached max count~");
        }
    }
}
