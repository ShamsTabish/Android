package com.acadgild.threadsdeamoapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ssm2349 on 5/22/16.
 */
public class ToastRaiser implements Runnable{
    Context context;
    ToastRaiser( Context context){
        this.context=context;
    }
    int i=0;

    void setI(int i){this.i=i;}

    @Override
    public void run() {
        Toast.makeText(context, "Status = " + i + "%", Toast.LENGTH_SHORT).show();
    }
}
