package com.baibian.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.baibian.listener.ReceiverTasksHelper;

public class TasksReceiver extends BroadcastReceiver {

    private ReceiverTasksHelper mHelper;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        mHelper.doTasks();
    }
    public void setImageLoadingHelper(ReceiverTasksHelper helper){
        this.mHelper = helper;
    }
}
