package com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit;

import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.OpenNotify;
import com.usermindarchive.h.internationalspacestationpasses.R;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * OpenNotifyDataInterface is the client interface for the Retrofit instance.
 * It contains dynamic part of the URL and methods to complete the network call.
 */

public interface OpenNotifyDataInterface {
//    Passing the latitude and longitude values of the Device and completing the URL to make the network call.
    @GET("iss-pass.json?")
    Call<OpenNotify> getData(@Query("lat") Double lat, @Query("lon") Double lon);
}