package com.acadgild.experimentalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdapterDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        String subject[]={"Java","Android ","VB","Spark.."};
//        Subject []subjects=new Subject[3];
        List<Subject> subjects=new ArrayList<Subject>();
        subjects.add(new Subject(1,"Java","Tab"));
        subjects.add(new Subject(2,"Andro"," Sam"));
        subjects.add(new Subject(3,"Spark","Tabi"));


        setContentView(R.layout.activity_adapter_demo);
        GridView subjectsView=(GridView)findViewById(R.id.subjects);
        SubjectDisplayAdapter myAdapt=new SubjectDisplayAdapter(subjects,this);
//        ArrayAdapter<String> myAdapt=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,subject);
        subjectsView.setAdapter(myAdapt);
        //subjects.add(new Subject(4,"Hadoop","naaz"));
        subjects.add(new Subject(34,"Magic","TTTT"));
        myAdapt.notifyDataSetChanged();

        subjects.remove(2);
        myAdapt.notifyDataSetChanged();

        Intent cal=new Intent(this,DateTime.class);
        startActivity(cal);
    }
}
