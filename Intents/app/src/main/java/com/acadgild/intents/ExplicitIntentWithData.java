package com.acadgild.intents;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ExplicitIntentWithData extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent_with_data);
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

        String institute=getResources().getString(R.string.institute);

        String instituteValue= (String)getIntent().getExtras().get(institute);

        textView=(TextView)findViewById(R.id.messageBox);

        String action=getIntent().getAction();
        String data=getIntent().getData().toString();

        textView.setText(institute+": "+instituteValue+"\n\n\n Action:"+action+"\n\n\n Data: "+data);

    }

}
