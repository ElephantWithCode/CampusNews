package com.baibian.tool;

import android.app.Application;

/**
 * Created by Ellly on 2017/8/24.
 */

public class MyApplication extends Application {
    private boolean isNightMode = false;
    public void setNightMode(boolean nightMode){
        isNightMode = nightMode;
    }
    public boolean isNightMode(){
        return isNightMode;
    }
}
