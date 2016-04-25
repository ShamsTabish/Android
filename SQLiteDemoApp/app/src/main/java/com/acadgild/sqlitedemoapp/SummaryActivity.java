package com.acadgild.sqlitedemoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
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

   public void insert(View v){
        EmployeeDB employeeDB=new EmployeeDB(SummaryActivity.this);
        employeeDB.insertEmployeeRecord();
    }
    public void show(View view){
        EmployeeDB employeeDB=new EmployeeDB(SummaryActivity.this);
        TextView listOfEmp=(TextView)findViewById(R.id.listOfEmployee);
        String employees=employeeDB.getAllEmployees();
        listOfEmp.setText(employees);
    }
    public void update(View v){
        EmployeeDB emp=new EmployeeDB(SummaryActivity.this);
        emp.updateEmployee();
    }
    public void deleteEmployee(View v){
        EmployeeDB emp=new EmployeeDB(SummaryActivity.this);
        emp.deleteEmployee();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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
