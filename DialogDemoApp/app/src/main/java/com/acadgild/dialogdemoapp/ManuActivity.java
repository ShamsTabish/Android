package com.acadgild.dialogdemoapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class ManuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu);
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
    public void callSimpleDialog(View view){
        SimpleDialog simpleDialog=new SimpleDialog();
        simpleDialog.show(getFragmentManager(), getResources().getString(R.string.TAG_SIMPLE_DIALOG));
    }

    public void callListDialog(View view){
        ListDialog myListDialog=new ListDialog();
        myListDialog.show(getFragmentManager(), getResources().getString(R.string.TAG_SIMPLE_DIALOG));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manu, menu);
        return true;
    }

    public void callCheckListDialog(View view){
        MultiSelectListDialog dialog=new MultiSelectListDialog();
        dialog.show(getFragmentManager(), getResources().getString(R.string.TAG_SIMPLE_DIALOG));
    }
    public boolean callCustomMirrorDialog(View view){
        CustomMirrorDialog mirrorDialog =new CustomMirrorDialog();
        mirrorDialog.show(getFragmentManager(), getResources().getString(R.string.TAG_SIMPLE_DIALOG));
        return true;
    }

    public boolean callDatePickerDialog(View view){
        DatePickerDialog datePicker=new DatePickerDialog(ManuActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(ManuActivity.this, "You choose "+dayOfMonth+"/"+monthOfYear+"/"+year, Toast.LENGTH_SHORT).show();
            }
        },2016,5,1);
        datePicker.setTitle("Your Wedding Anniversary Date!");

        datePicker.show();
        return true;
    }

    public boolean callTimePickerDialog(View view){
        TimePickerDialog dialog=new TimePickerDialog(ManuActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(ManuActivity.this, "Time: "+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
            }
        },12,30,false);
        dialog.setTitle("What time would you like to wake up");
        dialog.show();
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
