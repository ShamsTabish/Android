package com.acadgild.broadcastreceiverandnotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Set;

public class PhoneStateChangeReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Phone State",intent.getAction()+" "+intent.getData());
        Bundle bundle=intent.getExtras();
        Set<String> keys=bundle.keySet();
        for(String key:keys){
            Log.d(key,bundle.get(key).toString());
        }
    }
}
