package org.tensorflow.lite.examples.detection;

import android.location.Location;

public class CLocation extends Location {
    private boolean bUseMetricUnits = false;

    public CLocation(Location location) {
        this(location, true);
    }

    public CLocation(Location location, boolean useMetricUnits){
        super(location);
        this.bUseMetricUnits = useMetricUnits;
    }

    public boolean getUseMetricUnits(){
        return this.bUseMetricUnits;
    }

    public void setUserMetricUnits(boolean useMetricUnits){
        this.bUseMetricUnits = useMetricUnits;
    }

    @Override
    public float distanceTo(Location dest) {
        float nDistance = super.distanceTo(dest);
        if (this.getUseMetricUnits()){
            // convert distance from meter to feet
            nDistance = nDistance * 3.28083989f;
        }
        return nDistance;
    }

    @Override
    public double getAltitude() {
        double nAltitude = super.getAltitude();
        if (this.getUseMetricUnits()){
            // convert distance from meter to feet
            nAltitude = nAltitude * 3.28083989f;
        }
        return nAltitude;
    }

    @Override
    public float getSpeed() {
        float nSpeed = super.getSpeed() * 3.6f;
        if (this.getUseMetricUnits()){
            // convert distance from meter to feet
            nSpeed = super.getSpeed() * 2.23693629f;
        }
        return nSpeed;
    }
    public float getAccuracy() {
        float nAccuracy = super.getAccuracy();
        if (this.getUseMetricUnits()){
            // convert distance from meter to feet
            nAccuracy = nAccuracy * 3.28083989f;
        }
        return nAccuracy;
    }
}
