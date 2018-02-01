package com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit;

import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.Response;

import java.util.List;

/**
 * OpenNotifyEvent is a model class used by the OpenNotifyDataParser class to create an event
 * and update the UI depending on the Network Response.
 */

public class OpenNotifyEvent {

    private List<Response> response;

    //    Receives the List<Response> values from the OpenNotifyDataParser Event
    public OpenNotifyEvent(List<Response> response) {
        this.response = response;
    }

    //    Method to return the List<Response> value received from OpenNotifyDataParser
    public List<Response> getListOfResponse() {
        return response;
    }
}
