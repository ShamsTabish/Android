package com.acadgild.experimentalapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class SubjectDisplayAdapter extends BaseAdapter{
    List<Subject> subjectList;
Context context;
    public SubjectDisplayAdapter(List<Subject>s,Context c){
        subjectList=s;
        context=c;
    }
    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public Subject getItem(int i) {
        return subjectList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null) {
            convertView=LayoutInflater.from(context).inflate(R.layout.itemsubject,parent,false);
            TextView idn = (TextView) convertView.findViewById(R.id.myid);
            TextView subj = (TextView) convertView.findViewById(R.id.name);
            TextView teacher = (TextView) convertView.findViewById(R.id.teacher);

            idn.setText("Heyya");
            subj.setText(subjectList.get(position).name);
            teacher.setText(subjectList.get(position).teacher);
        }
        return convertView;
    }
}
