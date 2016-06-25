package sensor.com.acadgild.sessorsdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class SesnsorListDemo extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    EditText sensorView;
    TextView accelerometerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesnsor_list_demo);
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

        sensorView=(EditText)findViewById(R.id.sesnsorList);
        accelerometerView=(TextView)findViewById(R.id.accelerometer);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);


        //Listing The Sensors

        List<Sensor> sensorList=sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor sensor:sensorList){
            sensorView.append("Name: "+sensor.getName()+"\n");
            sensorView.append("Vendor: "+sensor.getVendor()+"\n");
            sensorView.append("Version: "+sensor.getVersion()+"\n\n----------------------------\n\n");
        }



        //Using Accelerometer
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sesnsor_list_demo, menu);
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
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            String x_axis=String.valueOf(event.values[0]);
            String y_axis=String.valueOf(event.values[1]);
            String z_axis=String.valueOf(event.values[2]);
            accelerometerView.setText("X-Axis:"+x_axis+"\nY-Axis:"+y_axis+"\nZ-Axis:"+z_axis);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
