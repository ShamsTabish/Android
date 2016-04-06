package com.acadgild.uiandevents;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ssm2349 on 4/2/16.
 */
public class EventListener implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Context c;
    private List<String> courseList;

    public EventListener(Context context){
        c=context;
    }
    @Override
    public void onClick(View v) {
        // v is the view (UI Component) from which the event was generated
        if(v instanceof CheckBox) {  // check if the event generated from CheckBox
            CheckBox checkBox = (CheckBox) v; // convert the view to CheckBox
            if (checkBox.isChecked()) {
                checkBox.setText("This is checked");
            } else {
                checkBox.setText("Unchecked Now");
            }

        }else if(v instanceof RadioButton) {//you can be very specific to a component
            // may use -->   if(v.getId() == R.id.radioButton)
            RadioButton radioButton=(RadioButton)v;
            if (radioButton.isChecked()) {
                radioButton.setText("This is on State");
            } else {
                radioButton.setText("Its Off Now!");
            }
            Toast.makeText(c, "Radio Button Checked", Toast.LENGTH_SHORT).show();
        }
    }


    public void setCourseList(List l){
        courseList=l;
    }  // Must be called before the event registration other wise courseList will be null



    //These method must be overridden to handle events on the spinner 
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedCourse=courseList.get(position);
        Toast.makeText(c, "The Selected Course is "+selectedCourse, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
