package com.acadgild.googlemapdemo;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ssm2349 on 7/2/16.
 */
public class GoogleGeoLocationClient {
    public LatLng getLatLngForTheLocation(String locationName){
        LatLng location=null;
        try {
            URI googleService=new  URI("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyDsTTIM5eu1FYRuPlPCCJ3luyPGxkfnAXc");
            //HttpGet
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return location;
    }

}
