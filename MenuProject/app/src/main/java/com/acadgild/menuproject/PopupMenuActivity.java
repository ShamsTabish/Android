package com.acadgild.menuproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuActivity extends AppCompatActivity {
    ImageView fruit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);
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

        fruit=(ImageView)findViewById(R.id.fruitImage);
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(PopupMenuActivity.this,fruit);

                popupMenu.inflate(R.menu.popup_menu);
                //popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId()==R.id.eat){
                            Toast.makeText(PopupMenuActivity.this, "I ate the Fruit", Toast.LENGTH_SHORT).show();
                            return true;
                        }else if(item.getItemId()==R.id.crush){
                            Toast.makeText(PopupMenuActivity.this, "I crushed it...", Toast.LENGTH_SHORT).show();
                            return true;
                        }

                        return false;
                    }
                });

                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        Toast.makeText(PopupMenuActivity.this, "I left it intact...", Toast.LENGTH_SHORT).show();
                    }
                });

                popupMenu.show();
            }
        });

    }

}
