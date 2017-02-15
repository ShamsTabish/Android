package com.acadgild.googlemapdemo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ssm2349 on 7/2/16.
 */
public class GPSReader implements LocationListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    final long INTERVAL = 1000 * 10;
    final long FAST_LOCATION_INTERVAL = 1000 * 5;
    LocationRequest locationRequest;
    GoogleApiClient googleApiClient;
    Location location;
    MarkerOptions markerOptions;

    Context context;
    GoogleMap map;
    GPSReader(Context ctx,MarkerOptions markerOptions,GoogleMap map) {
        context = ctx;
        this.markerOptions = markerOptions;
        this.map = map;

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (status != ConnectionResult.SUCCESS) {

        } else {
            locationRequest = new LocationRequest();
            locationRequest.setInterval(INTERVAL);
            locationRequest.setFastestInterval(FAST_LOCATION_INTERVAL);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            googleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            googleApiClient.connect();

        }
    }
    public void pauseGPSToSavePower() {
        stopLocationUpdate();
    }

    public void resumeGPS() {
        if(googleApiClient.isConnected())
            startLocationUpdate();
        updateMap();
    }

    private void startLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        updateMap();
    }
    private void stopLocationUpdate(){
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
    }
    public void disconnectGPS(){
        stopLocationUpdate();
        googleApiClient.disconnect();
        if (googleApiClient.isConnected())
            Toast.makeText(context, "Google Play Service is still connected", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Google Play Service Disconnected", Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(context, "Connected Successfully ...", Toast.LENGTH_LONG).show();
        startLocationUpdate();
        //updateMap();
    }

    @Override
    public void onConnectionSuspended(int i) {
        startLocationUpdate();
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location=location;
        Toast.makeText(context, "Location updated", Toast.LENGTH_SHORT).show();
        updateMap();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(context, "Could not Read your present Location..", Toast.LENGTH_SHORT).show();
    }

    public void updateMap(){
        if(location!=null) {
            LatLng myPresentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            markerOptions.position(myPresentLocation);
            markerOptions.title("My Current Position");
           // map.addMarker(markerOptions);

            CameraPosition newPosition=new  CameraPosition.Builder().target(myPresentLocation).build();

            map.animateCamera(CameraUpdateFactory.newCameraPosition(newPosition));

            Toast.makeText(context, "Location "+myPresentLocation.latitude+" - "+myPresentLocation.longitude+" ..", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Please enable GPS ..", Toast.LENGTH_SHORT).show();
        }

    }
}
