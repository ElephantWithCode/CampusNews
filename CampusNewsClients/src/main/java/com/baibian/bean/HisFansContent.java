package com.baibian.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/6.
 */

public class HisFansContent {
    public static class HisFans{
        private String portraitUrl;
        private String userName;
        private String userPersonalSignature;
        private long userLevel;
        private List<String> userHonors;
        public HisFans(String portraitUrl, String userName, String userPersonalSignature, long userLevel, List<String> userHonors){
            this.portraitUrl = portraitUrl;
            this.userName = userName;
            this.userHonors = userHonors;
            this.userPersonalSignature = userPersonalSignature;
            this.userLevel = userLevel;
        }
        public HisFans(){

        }
    }

    public static final List<HisFans> FANSES = new ArrayList<>();

    public static int COUNT = 25;

    public static void addFansItem(HisFans fans){
        FANSES.add(fans);
    }
    public static HisFans createFanItem(int position){
        return new HisFans();
    }

    static {
        for (int i = 0; i < COUNT; i++){
            addFansItem(createFanItem(i));
        }
    }
}
