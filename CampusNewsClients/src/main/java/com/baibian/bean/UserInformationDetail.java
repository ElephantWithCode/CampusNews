package com.baibian.bean;

import android.app.AlertDialog;

/**
 * Created by Ellly on 2017/8/11.
 */

public class UserInformationDetail {

    private String mBackGroundImageUrl;
    private String mUserPortraitImageUrl;

    private Boolean mIsFocused;

    private String mUserName;
    private String mUserSignature;

    private long mLikeAmounts;
    private long mFocusAmounts;
    private long mFocusedAmounts;

    private String mSchoolName;
    private String mGenderAgeConstellation;
    private String[] mAchievements;
    private String[] mLocations;
    private long mLevel;

    private long mTopics;
    private long mPoints;
    private long mPresentations;

    public static class Builder{

        private String mBackGroundImageUrl = "";
        private String mUserPortraitImageUrl = "";

        private Boolean mIsFocused = false;

        private String mUserName = "";
        private String mUserSignature = "";

        private long mLikeAmounts = 0;
        private long mFocusAmounts = 0;
        private long mFocusedAmounts = 0;

        private String mSchoolName = "";
        private String mGenderAgeConstellation = "";
        private String[] mAchievements;
        private long mLevel = 0;

        private long mTopics = 0;
        private long mPoints = 0;
        private long mPresentations = 0;

        public Builder setBackGroundImageUrl(String val){
            mBackGroundImageUrl = val;
            return this;
        }
        public UserInformationDetail build(){
            return new UserInformationDetail(this);
        }
    }
    private UserInformationDetail(Builder builder){
        mAchievements = builder.mAchievements;
        mBackGroundImageUrl = builder.mBackGroundImageUrl;
        mFocusAmounts = builder.mFocusAmounts;
        mFocusedAmounts = builder.mFocusedAmounts;
        mGenderAgeConstellation = builder.mGenderAgeConstellation;
        mIsFocused = builder.mIsFocused;
        mLevel = builder.mLevel;
        mLikeAmounts = builder.mLikeAmounts;
        mPoints = builder.mPoints;
        mPresentations = builder.mPresentations;
        mSchoolName = builder.mSchoolName;
        mUserName = builder.mUserName;
        mTopics = builder.mTopics;
        mUserPortraitImageUrl = builder.mUserPortraitImageUrl;
        mUserSignature = builder.mUserSignature;
    }
}
