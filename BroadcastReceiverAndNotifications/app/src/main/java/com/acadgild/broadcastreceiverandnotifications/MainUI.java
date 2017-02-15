package com.acadgild.broadcastreceiverandnotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainUI extends AppCompatActivity {
    int idForNotification=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       //Dynamic way of Receiving Broadcast Messages
//        IntentFilter intentFilter=new IntentFilter("android.intent.action.PHONE_STATE");
//        PhoneStateChangeReceiver receiver=new PhoneStateChangeReceiver();
//        registerReceiver(receiver,intentFilter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this);
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
            notificationBuilder.setContentText("Content Text");
            notificationBuilder.setSubText("SubText -Description");
            notificationBuilder.setContentTitle("Title "+idForNotification);
            Intent landingActivity=new Intent(this,NotificationLandingPage.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,1,landingActivity,0);

            notificationBuilder.setContentIntent(pendingIntent);

            notificationBuilder.addAction(R.mipmap.ic_launcher, "Call..", pendingIntent);
            notificationBuilder.addAction(R.mipmap.ic_launcher,"Dismiss!!",pendingIntent);

            NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(idForNotification++,notificationBuilder.build());


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
