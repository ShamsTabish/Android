package com.acadgild.adaptersandcalendar;
import android.widget.ListView;

import com.acadgild.adaptersandcalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssm2349 on 4/9/16.
 */
public class DataGenerator {
    List<String> cities;
    List<Integer> icons;

    public List<String> createCities(){
        cities =new ArrayList<String>() ;
        cities.add("Pune");
        cities.add("Bangalore");
        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Chennai");
        return cities;
    }

    public List<Integer> createIcons(){
        icons=new ArrayList<Integer>();
        icons.add(R.mipmap.ic_launcher);
        icons.add(R.mipmap.ic_launcher);
        icons.add(R.mipmap.ic_launcher);
        icons.add(R.mipmap.ic_launcher);
        icons.add(R.mipmap.ic_launcher);
        return  icons;
    }
}
