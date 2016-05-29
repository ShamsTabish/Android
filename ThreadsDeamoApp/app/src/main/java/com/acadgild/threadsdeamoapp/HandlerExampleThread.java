package com.acadgild.threadsdeamoapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by ssm2349 on 5/22/16.
 */
public class HandlerExampleThread extends Thread{
    Handler handler;
    HandlerExampleThread(Handler handler){
        this.handler=handler;
    }

    @Override
    public void run() {
        super.run();
        for (int i=0;i<=100;i+=10){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message=handler.obtainMessage();
            Bundle messageData=message.getData();

            messageData.putString("status",i+"% .....");

            handler.sendMessage(message);

        }
    }
}
