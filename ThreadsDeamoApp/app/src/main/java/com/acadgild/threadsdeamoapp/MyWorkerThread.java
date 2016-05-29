package com.acadgild.threadsdeamoapp;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class MyWorkerThread  extends Thread{
    Activity activity;
    MyWorkerThread(Activity activity){
        this.activity=activity;
    }

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        ToastRaiser toast=new ToastRaiser(activity);

        for (int i=0;i<=100;i+=10){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toast.setI(i);
            activity.runOnUiThread(toast);
        }
        Looper.loop();
    }
}
