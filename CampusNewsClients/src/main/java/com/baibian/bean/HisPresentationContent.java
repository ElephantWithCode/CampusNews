package com.baibian.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/5.
 */

public class HisPresentationContent {
    public static class HisPresentation{
        private String mTime;
        private String mPresentationTitle;
        private String mFavorAmount;
        private String mFocusAmount;

        public HisPresentation(String mFocusAmount, String mFavorAmount, String mPresentationTitle, String mTime){
            this.mPresentationTitle = mPresentationTitle;
            this.mFavorAmount = mFavorAmount;
            this.mFocusAmount = mFocusAmount;
            this.mTime = mTime;
        }
        public HisPresentation(){

        }
    }

    public static final List<HisPresentation> PRESENTATIONS = new ArrayList<>();

    public static int COUNT = 25;

    public static void addPresentationItem(HisPresentation presentation){
        PRESENTATIONS.add(presentation);
    }

    public static HisPresentation createPresentationItem(int i){
        return new HisPresentation();
    }

    static {
        for (int i = 0; i < COUNT; i++){
            addPresentationItem(createPresentationItem(i));
        }
    }
}
