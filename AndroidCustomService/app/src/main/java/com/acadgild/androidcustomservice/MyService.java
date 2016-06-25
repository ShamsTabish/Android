package com.acadgild.androidcustomservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    final String TAG = "MyService";
    Thread myThread;
    public MyService() {
        Log.d(TAG, "Constructor of Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate of Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "start of Service");
        if(intent!=null)
        Log.d(TAG, "Parameter Name:"+intent.getExtras().get("Name"));
        else
           Log.d(TAG,"Empty Parameters");
       myThread= new Thread() {
            @Override
            public void run() {
                super.run();
                for ( int i = 0;i < 1000; i += 10)
                {
                    Log.d(TAG, "i's value is " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        myThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //myThread.stop();
        Log.d(TAG, "Destroy of Service");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "Mem. Low of Service");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "Memory Released of Service");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Unbind of Service");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "OnRebind of Service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        
        Log.d(TAG, "onbind of Service");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
