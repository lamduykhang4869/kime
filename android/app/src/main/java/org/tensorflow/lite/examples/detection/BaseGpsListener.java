package org.tensorflow.lite.examples.detection;
import android.location.Location;
import android.location.LocationListener;
import android.location.GpsStatus;
import android.os.Bundle;

public interface BaseGpsListener extends LocationListener, GpsStatus.Listener{
    public void onLocationChanged(Location location);

    public void onProviderDisabled(String provider);

    public void onProviderEnabled(String provider);

    public void onStatusChanged(String provider, int status, Bundle extras);

    public void onGpsStatusChanged(int event);
}
