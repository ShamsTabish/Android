package com.acadgild.experimentalapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        TimePicker p=new TimePicker();

        p.show(getSupportFragmentManager(),"");
    }

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar= Calendar.getInstance();
            int hour=calendar.get(Calendar.HOUR);
            int minute=calendar.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(),this,hour,minute,false);
        }

        @Override
        public void onTimeSet(android.widget.TimePicker timePicker, int i, int i1) {

        }
    }
}
