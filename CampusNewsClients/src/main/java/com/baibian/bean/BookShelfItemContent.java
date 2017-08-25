package com.baibian.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/24.
 */

public class BookShelfItemContent {

    public static class BookShelf{

        private String mImageUrl;

        public BookShelf(String mImageUrl){
            this.mImageUrl = mImageUrl;
        }

        public BookShelf(){}

        public String getmImageUrl() {
            return mImageUrl;
        }
        public void setmImageUrl(String mImageUrl) {
            this.mImageUrl = mImageUrl;
        }
    }

    public static final List<BookShelf> mData = new ArrayList<>();

    public static int COUNT = 7;

    public static void addItem(BookShelf bookShelf){
        mData.add(bookShelf);
    }
    static {
        for (int i = 0; i < COUNT; i++){
            addItem(new BookShelf());
        }
    }
}
