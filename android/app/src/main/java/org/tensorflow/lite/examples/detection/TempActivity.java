package org.tensorflow.lite.examples.detection;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Formatter;
import java.util.Locale;

public class TempActivity extends AppCompatActivity implements BaseGpsListener{
    CheckBox cb;
    TextView txtCurrentSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        cb = findViewById(R.id.meterCb);
        txtCurrentSpeed = findViewById(R.id.currentSpeed);

        Log.wtf("Hoang", "1");

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Log.wtf("Hoang", "2");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        Log.wtf("Hoang", "3");
        this.updateSpeed(null);
        Log.wtf("Hoang", "4");

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                TempActivity.this.updateSpeed(null);
            }
        });
    }
    
    public void finish() {
        super.finish();
        System.exit(0);
    }

    private void updateSpeed(CLocation location) {
        float nCurrentSpeed = 0;

        if (location != null){
            location.setUserMetricUnits(this.useMetricUnits());
            nCurrentSpeed = location.getSpeed();
        }

        Formatter fmt = new Formatter(new StringBuilder());
        fmt.format(Locale.US, "%5.1f", nCurrentSpeed);
        String strCurrentSpeed = fmt.toString();
        strCurrentSpeed = strCurrentSpeed.replace(' ', '0');
        String strUnit = "miles/hour";
        if (this.useMetricUnits()){
            strUnit = "km/h";
        }
        
        txtCurrentSpeed = findViewById(R.id.currentSpeed);
        txtCurrentSpeed.setText(strCurrentSpeed + " " + strUnit);
        
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null){
            CLocation myLocation = new CLocation(location, this.useMetricUnits());
            this.updateSpeed(myLocation);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onGpsStatusChanged(int event) {

    }

    private boolean useMetricUnits() {
        CheckBox cb = findViewById(R.id.meterCb);
        return cb.isChecked();
    }
}