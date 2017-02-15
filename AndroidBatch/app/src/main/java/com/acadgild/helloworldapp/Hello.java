package com.acadgild.helloworldapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Toast;

public class Hello extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        Toast t=Toast.makeText(Hello.this,"UUUUU....",Toast.LENGTH_LONG);
        t.setText("Myy");
        t.show();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.setText("Yeppi..");
        t.show();

    }
}
