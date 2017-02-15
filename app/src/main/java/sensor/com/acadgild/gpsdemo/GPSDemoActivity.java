package sensor.com.acadgild.gpsdemo;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class GPSDemoActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    final long INTERVAL = 1000 * 10;
    final long FAST_INTERVAL = 1000 * 5;

    LocationRequest locationRequest;
    GoogleApiClient googleApiClient;
    Location location;

    TextView statusView, gpsUpdateView;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsdemo);
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


        statusView = (TextView) findViewById(R.id.statusBar);
        gpsUpdateView = (TextView) findViewById(R.id.gps_info);
        updateButton = (Button) findViewById(R.id.updateButton);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            finish();
        }


        locationRequest = new LocationRequest();
        locationRequest.setInterval(INTERVAL);
        locationRequest.setFastestInterval(FAST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
        statusView.setText("Connection Established");
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopGPSUpdates();
        googleApiClient.disconnect();
        if (googleApiClient.isConnected())
            Toast.makeText(GPSDemoActivity.this, "Google Play Service is still connected", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(GPSDemoActivity.this, "Google Play Service Disconnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        statusView.setText("App Resumed");
        updateTheUI();
    }

    private void startGPSUpdates(){
        if (googleApiClient.isConnected()) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            PendingResult<Status> pendingIntent = LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }


    private void updateTheUI() {
        StringBuffer gpsInfo;
        if (location != null) {
            gpsInfo = new StringBuffer();
            String latitude = String.valueOf(location.getLatitude());
            String longitude = String.valueOf(location.getLongitude());
            String provider = location.getProvider();
            String accuracy = String.valueOf(location.getAccuracy());

            gpsInfo.append("Latitude : " + latitude + "\n");
            gpsInfo.append("Longitude :" + longitude + "\n");
            gpsInfo.append("Accuracy : " + accuracy + "\n");
            gpsInfo.append("Provider : " + provider);
        } else {
            gpsInfo = new StringBuffer("No GPS Info could be loaded!!!");
        }
        gpsUpdateView.setText(gpsInfo);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopGPSUpdates();
    }
    private void stopGPSUpdates(){
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gpsdemo, menu);
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

    @Override
    public void onConnected(Bundle bundle) {
        statusView.setText("Connected to Google Service");
        startGPSUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        statusView.setText("Connection Suspended");
        stopGPSUpdates();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        statusView.setText("Connection Failed!!!");
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location=location;
        statusView.setText("Location Changed");
        updateTheUI();
    }
}
