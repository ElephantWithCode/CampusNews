package com.baibian.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellly on 2017/8/5.
 */

public class HisUserContent {

    public static class User{

        private String portraitUrl;
        private String userName;
        private String userPersonalSignal;

        public User(String portraitUrl, String userName, String userPersonalSignal){
            this.portraitUrl = portraitUrl;
            this.userName = userName;
            this.userPersonalSignal = userPersonalSignal;
        }
    }

    public static final List<User> USERS = new ArrayList<>();
    public static int COUNT = 25;

    public static void addUserItem(User user){
        USERS.add(user);
    }
/*
    public void setItemCount(int count){
        COUNT = count;
    }*/

    public static List<User> createUserList(){
        return new ArrayList<>();
    }
    public static User createUserItem(int i){
        return new User("", "name"+i, "signal"+i);
    }
    static {
        for (int i = 0; i < COUNT; i++){
            addUserItem(createUserItem(i));
        }
    }
}
