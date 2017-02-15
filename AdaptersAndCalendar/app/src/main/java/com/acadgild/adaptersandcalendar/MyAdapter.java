package com.acadgild.adaptersandcalendar;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter  extends BaseAdapter{
    Context context;
    List<String> cities;
    List<Integer> icons;
    LayoutInflater listItemCreater;
    public MyAdapter(Context context, List<String> cities,List<Integer>icons){
        this.context=context;
        this.cities=cities;
        listItemCreater=(LayoutInflater.from(context));
        this.icons=icons;
    }
    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=listItemCreater.inflate(R.layout.city_layout,parent,false);
            TextView cityName=(TextView)convertView.findViewById(R.id.cityName);
            cityName.setText(cities.get(position));

            ImageView cityImage=(ImageView)convertView.findViewById(R.id.cityImage);
            cityImage.setImageResource(icons.get(position));

            cityImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, "City: "+cities.get(position), Toast.LENGTH_SHORT).show();
                    Snackbar.make(v,"City: "+cities.get(position),Snackbar.LENGTH_SHORT).show();
                }
            });
        }


        return convertView;
    }
}
