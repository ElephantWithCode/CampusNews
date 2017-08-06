package com.baibian.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/5.
 */

public class HisTopicContent {

    public static class HisTopic{
        private String mTopicTitle;
        private String mTopicSubTitle;
        private String mTime;
        private String mFavorAmount;
        private String mFocusAmount;

        public HisTopic(String mTopicTitle, String mTopicSubTitle, String mTime, String mFavorAmount, String mFocusAmount){
            this.mTopicTitle = mTopicTitle;
            this.mTopicSubTitle = mTopicSubTitle;
            this.mTime = mTime;
            this.mFavorAmount = mFavorAmount;
            this.mFocusAmount = mFocusAmount;
        }

        public HisTopic(){

        }
    }

    public static final List<HisTopic> TOPICS = new ArrayList<>();

    public static int COUNT = 25;

    public static void addTopicItem(HisTopic topic){
        TOPICS.add(topic);
    }

    public static HisTopic createTopicItem(int i){
        return new HisTopic();
    }

    static {
        for (int i = 0; i < COUNT; i++){
            addTopicItem(createTopicItem(i));
        }
    }
}
