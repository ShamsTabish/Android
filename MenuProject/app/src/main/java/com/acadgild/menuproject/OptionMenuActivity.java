package com.acadgild.menuproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SubMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_option_menu, menu);

        String share=getResources().getString(R.string.menu_share);
        String say_hi=getResources().getString(R.string.menu_say_hi);
        String exit=getResources().getString(R.string.menu_exit);

        menu.add(0,// Group ID
                0,//Item ID
                1,//Order In the menu
                share //Titile of menu
        );
        menu.add(0,1,2,say_hi);
        menu.add(0,2,3,exit);
        menu.add(0,10,5,"Call Context Menu Activity");
        menu.add(0,11,6,"Call Popup Menu Activity");

        SubMenu subMenu =menu.addSubMenu("Communicate");
        subMenu.add(0,4,1,"Call");
        subMenu.add(0,5,2,"SMS");

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
            Toast.makeText(OptionMenuActivity.this, "Settings Menu Selected", Toast.LENGTH_SHORT).show();
            return true;
        }else if(/*id==R.id.share ||*/ id==0){
            Intent intent =new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://google.com"));
            startActivity(intent);
            return true;
        }else if(id == R.id.sayHi || id==1){
            Toast.makeText(OptionMenuActivity.this, "Hello how are you today.", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id ==  R.id.exit || id==2){
            System.exit(0);
        }else
         if(id == 4){
             Toast.makeText(OptionMenuActivity.this, "Calling ....", Toast.LENGTH_SHORT).show();
             return true;
         }else if(id == 10){
             Intent contextMenuCall=new Intent(OptionMenuActivity.this,ContextMenu.class);
             startActivity(contextMenuCall);
         }
            else if(id == 11){
             Intent popupMenuCall=new Intent(OptionMenuActivity.this,PopupMenuActivity.class);
             startActivity(popupMenuCall);
         }


        return super.onOptionsItemSelected(item);
    }
}
