package com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit;

import android.util.Log;

import com.usermindarchive.h.internationalspacestationpasses.Model.Dagger.OpenNotifyBuild;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.*;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.*;

/**
 * OpenNotifyDataParser is used to pass the latitude and longitude values of the device location
 * to make the network call and get the required response from network.
 */

public class OpenNotifyDataParser {

@Inject
        @Named("Dummy")
      Retrofit retrofitAdapter;
    private OpenNotifyDataInterface openNotifyDataInterface;

    // Passing the Retrofit instance created in OpenNotifyBuild
  /*  public OpenNotifyDataParser(Retrofit retrofitAdapter) {
        this.retrofitAdapter=retrofitAdapter;
        getRetrofitClientReference();

    }*/

    public OpenNotifyDataParser(){
        OpenNotifyBuild.getOpenNotifyBuild().getOpenNotifyComponent().inject(this);
        getRetrofitClientReference();
    }

    // Creating the reference for the OpenNotifyDataInterface
    public void getRetrofitClientReference(){
         this.openNotifyDataInterface = retrofitAdapter.create(OpenNotifyDataInterface.class);
    }

    // Method to get the network response with the latitude and longitude values from Device
    public void getJSONDataFromAPI(Double lat,Double lon){

        // Passing latitude and longitude values
        Call<OpenNotify> call=openNotifyDataInterface.getData(lat,lon);

        // Making the network call
        call.enqueue(new Callback<OpenNotify>() {

            // Method that execute when call is successful
            @Override
            public void onResponse(Call<OpenNotify> call, retrofit2.Response<OpenNotify> response) {

                // checking the Network Response for any Error

                if(response.errorBody() != null){
                    Log.e("Response Body error","Error-"+response.body().getMessage().toString());
                }else {

                    // Event that passes the response to presenter and make necessary update on UI
                    EventBus.getDefault().post(new OpenNotifyEvent(response.body().getResponse()));

                }
            }
            // Method that execute when call is Failed
            @Override
            public void onFailure(Call<OpenNotify> call, Throwable t) {

                Log.e("406","Failed"+t.getMessage().toString());
                // Event that passes the OnFailure response to presenter and make necessary update on UI
                EventBus.getDefault().post(new OpenNotifyFailureEvent(t.getMessage().toString()));
            }
        });
    }
}
