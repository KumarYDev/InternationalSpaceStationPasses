package com.usermindarchive.h.internationalspacestationpasses.Utils.GPSDevice;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.usermindarchive.h.internationalspacestationpasses.R;

import org.greenrobot.eventbus.EventBus;

/**
 * GPSDevice is used to get the Device latitude and Longitude using the GPS Provider or Network provider.
 */

public class GPSDevice {
    private LocationListener locationListener;
    private LocationManager locationManager;
    private Context context;
    private String locationListenerTag="LOCATIONS";
    private String ProviderTag="Provider";
    private String GPSProviderTag="GPS_PROVIDER";
    private String NetworkProviderTag="NETWORK_PROVIDER";

    public GPSDevice(Context context) {
        super();
        this.context = context;

        // Creating the LocationManager
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        gpsDevice();
    }

    // Method to create request for getting Latitude and Longitude values
    public void getDeviceLocation() {



        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //Creating location Request using GPS_PROVIDER
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.e(ProviderTag, GPSProviderTag);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } else
                //Creating location Request using NETWORK_PROVIDER
                if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                Log.e(ProviderTag, NetworkProviderTag);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }else{
                Log.e("###","Failed");
                Toast.makeText(context, context.getResources().getString(R.string.deviceLocationErrorToastMessage),Toast.LENGTH_LONG).show();
            }
        }
    }



    //Method to get the Latitude and Longitude values
    public void gpsDevice(){

        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(location.getLatitude()!=0&&location.getLongitude()!=0){
//                    Getting the Latitude and Longitude values

                    Log.e(locationListenerTag,location.getLatitude()+"\n"+location.getLongitude());

                    // Event that passes the Latitude and Longitude values to presenter and make network call
                    EventBus.getDefault().post(new GPSDeviceEvent(location.getLatitude(),location.getLongitude()));

                    // Removes the Location Request Updates
                    locationManager.removeUpdates(locationListener);
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.e("###",s);
            }

            @Override
            public void onProviderEnabled(String s) {
            Log.e("###",s);
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.e("###",s);
            }
        };
    }


}
