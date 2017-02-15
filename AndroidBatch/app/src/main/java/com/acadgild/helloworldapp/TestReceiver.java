package com.acadgild.helloworldapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class TestReceiver extends BroadcastReceiver {
    public TestReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        intent.getAction()
        Toast t=Toast.makeText(context,"UUUUU....",Toast.LENGTH_LONG);
        t.setText("Myy");
        t.show();
        t.setText("Yeppi..");
        t.show();
        Log.wtf("Hello ","Thus is a log message..");
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
