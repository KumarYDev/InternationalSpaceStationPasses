package com.usermindarchive.h.internationalspacestationpasses.Utils.GPSDevice;

/**
 * GPSDeviceEvent is a model class used by the GPSDevice class to create an event
 * and pass the latitude and longitude values from the device.
 */

public class GPSDeviceEvent {


    private final double latitude;
    private final double longitude;

    //    Receives the latitude and longitude values from the GPSDevice Event
    public GPSDeviceEvent(double latitude, double longitude) {
        this.latitude=latitude;
        this.longitude=longitude;
    }

    //    Method to return the latitude value received from GPSDevice.
    public double getLatitude() {
        return latitude;
    }

    //    Method to return the longitude value received from GPSDevice.
    public double getLongitude() {
        return longitude;
    }
}
