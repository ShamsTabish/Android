package com.acadgild.uiandevents;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UIDemoActivity extends AppCompatActivity {
    EditText emailField;
    Button displayButton;
    RadioButton radioButton;
    CheckBox chkBox;

    Spinner courseListSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uidemo);
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
        //Load the Component objects from XML file
        emailField=(EditText)findViewById(R.id.emailBox);
        displayButton=(Button)findViewById(R.id.showButton);
        radioButton=(RadioButton)findViewById(R.id.radioButton);
        chkBox=(CheckBox)findViewById(R.id.checkBox);
        courseListSpinner=(Spinner)findViewById(R.id.spinner);

        //Create an object of a Common Object
        EventListener listener= new EventListener(UIDemoActivity.this);
        List <String> courseList=new ArrayList<String>();

        courseList.add("Information Security");
        courseList.add("Operating System");
        courseList.add("Android ");

        listener.setCourseList(courseList);// Its compulsory to be passed otherwise the courseList in the EventListener class will be null
        courseListSpinner.setOnItemSelectedListener(listener);

        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,courseList);

        courseListSpinner.setAdapter(spinnerAdapter);

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailField.getText().toString();
                Toast.makeText(UIDemoActivity.this, "Email: "+email, Toast.LENGTH_SHORT).show();
                radioButton.setChecked(!radioButton.isChecked());

            }
        });
        chkBox.setOnClickListener(new EventListener(UIDemoActivity.this));  // Registering an eventlistener for ckeckbox
                                                                            // The callback method is defined inside EventListener CLass.
        radioButton.setOnClickListener(new EventListener(UIDemoActivity.this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uidemo, menu);
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
