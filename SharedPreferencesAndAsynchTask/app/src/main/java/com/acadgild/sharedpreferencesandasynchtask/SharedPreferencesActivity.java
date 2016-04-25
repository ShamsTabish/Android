package com.acadgild.sharedpreferencesandasynchtask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        simplePreferenceExample();

    }


    public void doTimeConsumingOperation(int start){
        for (int i=start;i<1000;i++){
            try {
                Thread.sleep(1000);
                Log.i("Sleep",new Integer(i).toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    void simplePreferenceExample(){


        // Writing the key and values
        SharedPreferences preferences=getSharedPreferences("names",MODE_PRIVATE);

        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("name1","Rahul");
        editor.putString("name2","Pradeep");
        editor.commit();

        Toast.makeText(SharedPreferencesActivity.this, "Values Stored Successfully", Toast.LENGTH_SHORT).show();

        //Reading the values using keys
        SharedPreferences readPreferences=getSharedPreferences("names", MODE_PRIVATE);
        String name1=readPreferences.getString("name1", "Unknown");
        String name2=readPreferences.getString("name2","Unknown");
        Toast.makeText(SharedPreferencesActivity.this, "name1="+name1+" name2="+name2, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shared_preferences, menu);
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
//            doTimeConsumingOperation(1);
            MyAsyncOperation timeConsumingOPeration=new MyAsyncOperation();
            timeConsumingOPeration.execute(1);


            Intent   settingsPreferenceIntent=new Intent(SharedPreferencesActivity.this,MySettingPreference.class);
            startActivity(settingsPreferenceIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
