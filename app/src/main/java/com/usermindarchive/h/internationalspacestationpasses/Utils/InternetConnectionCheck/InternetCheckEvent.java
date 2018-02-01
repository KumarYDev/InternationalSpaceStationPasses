package com.usermindarchive.h.internationalspacestationpasses.Utils.InternetConnectionCheck;

/**
 * InternetCheckEvent is a model class used by the InternetCheck class to create an event
 * and update the UI depending on the Network Status.
 */

public class InternetCheckEvent {

    private  boolean isConnected;


    //    Receives the Boolean values from the InternetCheck Event
    public InternetCheckEvent(boolean isConnected) {
        this.isConnected=isConnected;

    }

//    Method to return the Boolean value received from InternetCheck
    public boolean getConnectionStatus() {
        return isConnected;
    }

}
