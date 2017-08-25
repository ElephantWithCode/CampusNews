package com.baibian.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/25.
 */

public class MyCollectionItemContent {

    public static final List<MyCollectionItem> mData = new ArrayList<>();
    public static int COUNT = 10;

    public static void addItem(MyCollectionItem i){
        mData.add(i);
    }

    static {
        for (int i = 0; i < COUNT; i++){
            addItem(new MyCollectionItem());
        }
    }
    public static class MyCollectionItem{
        private String mTitle;
        private String mContent;
        public MyCollectionItem(){}
        public MyCollectionItem(String mTitle, String mContent){
            this.mContent = mContent;
            this.mTitle = mTitle;
        }
    }


}
