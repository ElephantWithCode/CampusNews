package com.baibian.tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.baibian.activity.OtherInformationActivity;

/**
 * Created by Ellly on 2017/8/11.
 */

public class GoToInformationTool {
    public static int RESULT_CODE = 100;
    public static void goTo(Activity context){
        Intent newIntent = new Intent(context, OtherInformationActivity.class);
        context.startActivityForResult(newIntent, RESULT_CODE);
    }
}
