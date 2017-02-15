package com.acadgild.googlemapdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GPSReader gpsReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //   Intent intent=new Intent(MapsActivity.this, SpeechTest.class);
        //   startActivity(intent);

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gpsReader!=null){
            gpsReader.resumeGPS();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(gpsReader!=null){
            gpsReader.pauseGPSToSavePower();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(gpsReader!=null)
            gpsReader.disconnectGPS();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    static int touchCounter = 0;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        // Add a marker in Sydney and move the camera
        LatLng myLocation = new LatLng(-34, 151);

        MarkerOptions sydneyOptions = new MarkerOptions();
        sydneyOptions.position(myLocation);
        sydneyOptions.title("Here I'm");

        mMap.addMarker(sydneyOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));


        LatLng india = new LatLng(18, 77);
        MarkerOptions presentLocation = new MarkerOptions().position(india).title("India").snippet("Lovely India.").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
        mMap.addMarker(presentLocation);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Map Clicked ", Toast.LENGTH_SHORT).show();
                switch (touchCounter % 4) {
                    case 0:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case 1:
                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                    case 2:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 3:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                }
                touchCounter++;
            }
        });

        //mMap.addCircle(new CircleOptions().center(india).fillColor(Color.BLUE).radius(50000).visible(true));
        mMap.addPolyline(new PolylineOptions().add(india).add(myLocation).visible(true).color(Color.GREEN));

        //CameraPosition newPosition=new  CameraPosition.Builder().target(new LatLng(17.832123,77.335534)).zoom(12).build();

        //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(newPosition));
        if (gpsReader == null)
            gpsReader = new GPSReader(this, presentLocation,mMap);
    }
}
