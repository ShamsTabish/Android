package com.acadgild.threadsdeamoapp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by ssm2349 on 5/22/16.
 */
public class MessageHandler extends Handler {
    Context context;
    MessageHandler (Context context){
        this.context=context;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        String status =msg.getData().getString("status");

        if(status!=null && !status.equals("")){
            Toast.makeText(context, "Status = "+status, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "No Update...", Toast.LENGTH_SHORT).show();
        }
    }
}
