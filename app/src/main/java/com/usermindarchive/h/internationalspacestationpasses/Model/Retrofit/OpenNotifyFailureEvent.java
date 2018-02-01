package com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit;

/**
 * Created by HERO on 2/1/2018.
 */

public class OpenNotifyFailureEvent {

    private  String onFailureResponse;

    //    Receives the onFailure Response from the OpenNotifyDataParser Failure Event
    public OpenNotifyFailureEvent(String onFailureResponse) {
        this.onFailureResponse=onFailureResponse;
    }

    //    Method to return the onFailure Response received from OpenNotifyDataParser
    public String getOnFailureResponse() {
        return onFailureResponse;
    }
}
