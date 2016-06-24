package com.example.captaincode.gpslocation;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by captaincode on 16/06/16.
 */
public class GPSService extends Service implements LocationListener {
    private double latitude, longitude;
    private boolean isactive;
    private LocationManager locationManager;
    private Context context;
    private Location location;

    public GPSService(Context context){
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getLocation(){
        try{
            this.locationManager = (LocationManager) this.context.getSystemService(LOCATION_SERVICE);
            this.isactive = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        if(this.isactive){
            if(this.locationManager != null){
                this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000*60, 10, this);
                this.location = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                this.latitude = this.location.getLatitude();
                this.longitude = this.location.getLongitude();
            }
        }
    }

    public Location getObjectLocation(){
        return this.location;
    }

    public String result(){
        return String.valueOf(this.latitude)+", "+String.valueOf(this.longitude);
    }
}
