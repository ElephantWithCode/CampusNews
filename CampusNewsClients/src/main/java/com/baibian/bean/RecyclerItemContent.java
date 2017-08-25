package com.baibian.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/24.
 */

public class RecyclerItemContent {

    public static final List<RecyclerItem> mData = new ArrayList<>();
    public static int COUNT = 15;

    public enum RecyclerIssueType{
        POINT, REQUEST, ISSUE
    }
    public static class RecyclerItem{
        private RecyclerIssueType mType;
        private String mTitle;
        private String mContent;
        public RecyclerItem(){}

        public String getmContent() {
            return mContent;
        }

        public void setmContent(String mContent) {
            this.mContent = mContent;
        }

        public String getmTitle() {
            return mTitle;
        }

        public void setmTitle(String mTitle) {
            this.mTitle = mTitle;
        }

        public RecyclerIssueType getmType() {
            return mType;
        }

        public void setmType(RecyclerIssueType mType) {
            this.mType = mType;
        }
    }

    public static void addItem(RecyclerItem recyclerItem){
        mData.add(recyclerItem);
    }
    static {
        for (int i = 0; i < COUNT; i++){
            addItem(new RecyclerItem());
        }
    }
}
