package com.acadgild.actionbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SimpleActionBar extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_action_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Simple ActionBar");
        toolbar.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));
        toolbar.setSubtitle("Using Tool Bar ...");
        toolbar.setNavigationIcon(R.drawable.ic_restore_page_white_24dp);
        setSupportActionBar(toolbar);


        //ActionBar actionBar=getSupportActionBar();
        //actionBar.setTitle("New Title");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleActionBar.this, "My Actionbar Nav...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Toast.makeText(SimpleActionBar.this, "Selected " + item.getTitle(), Toast.LENGTH_SHORT).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent tabbedActionBar=new Intent(SimpleActionBar.this,TabbedActionBarActivity.class);
            startActivity(tabbedActionBar);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
