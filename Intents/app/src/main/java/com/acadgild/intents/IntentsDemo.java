package com.acadgild.intents;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class IntentsDemo extends AppCompatActivity {
    Button explicitCaller;
    Button implicitIntent1;
    Button explicitIntentWithData;
    Button getExplicitIntentWithResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_demo);
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

        explicitCaller=(Button)findViewById(R.id.explicitIntent);

        explicitCaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent=new Intent(IntentsDemo.this,AboutUsActivity.class);
                startActivity(explicitIntent);
            }
        });

        implicitIntent1=(Button)findViewById(R.id.implicitIntent1);
        implicitIntent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent=new Intent(Intent.ACTION_VIEW);
                shareIntent.setData(Uri.parse("http://google.com"));

                Bundle bundle=new Bundle();
                bundle.putString(getResources().getString(R.string.institute),"Bangalore");
                shareIntent.putExtras(bundle);
                startActivity(shareIntent);
            }
        });


        explicitIntentWithData=(Button)findViewById(R.id.explicitIntentWithData);
        explicitIntentWithData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent=new Intent(IntentsDemo.this,ExplicitIntentWithData.class);
                Resources resource=getResources();

                explicitIntent.setData(Uri.parse("http://google.com"));
                explicitIntent.setAction(Intent.ACTION_VIEW);

                String institute=resource.getString(R.string.institute);
                String instituteValue=resource.getString(R.string.instituteValue);
                explicitIntent.putExtra(institute,instituteValue);
                startActivity(explicitIntent);
            }
        });


        getExplicitIntentWithResult=(Button)findViewById(R.id.explicitIntentWithResult);
        getExplicitIntentWithResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntentsDemo.this,ExplicitIntentWithResult.class);
                Bundle bundle=new Bundle();
                bundle.putString("city", "Bangalore");
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && data!=null){
            Toast.makeText(IntentsDemo.this,"Result "+data.getExtras().get("name").toString(),Toast.LENGTH_LONG).show();
        }else if(resultCode == RESULT_CANCELED){
            Toast.makeText(IntentsDemo.this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intents_demo, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
