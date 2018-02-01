package com.usermindarchive.h.internationalspacestationpasses.Presenter;

import com.usermindarchive.h.internationalspacestationpasses.Model.Dagger.OpenNotifyBuild;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyFailureEvent;
import com.usermindarchive.h.internationalspacestationpasses.Utils.GPSDevice.GPSDevice;
import com.usermindarchive.h.internationalspacestationpasses.Utils.GPSDevice.GPSDeviceEvent;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyDataParser;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyEvent;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

/**
 * MainFragmentPresenter is the Presenter class that is responsible to communicate between UI and model classes .
 */

public class MainFragmentPresenter {

    //Injecting the Dagger values
    @Inject OpenNotifyDataParser openNotifyDataParser;
    @Inject GPSDevice gpsDevice;

    private MainFragmentPresenterInterface mainFragmentPresenterInterface;

    //Passing the Reference of the View Class
    public MainFragmentPresenter(MainFragmentPresenterInterface mainFragmentPresenterInterface) {
        this.mainFragmentPresenterInterface = mainFragmentPresenterInterface;

        // Injecting the Dagger Component so that the Dagger provided values can be used.
        OpenNotifyBuild.getOpenNotifyBuild().getOpenNotifyComponent().inject(this);
    }

    // Registering for the EventBus Events
    public void onStart(){

    EventBus.getDefault().register(this);

    }

    // Removing the Registered Events
    public void onStop(){
    EventBus.getDefault().unregister(this);

}

    // Registered Event method for OpenNotifyEvent
    // Network Response Event
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOpenNotifyEvent(OpenNotifyEvent openNotifyEvent) {

        // Passing the Network Response to UI
        mainFragmentPresenterInterface.sendOpenNotifyAPIResponseData(openNotifyEvent.getListOfResponse());

    }

    // Registered Event method for OpenNotifyFailureEvent
    // Network Failure Response Event
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOpenNotifyFailureEvent(OpenNotifyFailureEvent openNotifyFailureEvent) {

        // Passing the Network Response to UI
        mainFragmentPresenterInterface.sendOpenNotifyAPIFailureResponseData(openNotifyFailureEvent.getOnFailureResponse());

    }

    //// Registered Event method for GPSDeviceEvent for getting the Latitude and Longitude of the device
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGPSDeviceEvent(GPSDeviceEvent gpsDeviceEvent) {
        // Passing the Device Latitude and Longitude values to make network call
        makeOpenNotifyAPIcall(gpsDeviceEvent.getLatitude(),gpsDeviceEvent.getLongitude());

    }



    // Passing the Latitude and Longitude values to make the network call
    public void makeOpenNotifyAPIcall(Double lat, Double lon) {
      openNotifyDataParser.getJSONDataFromAPI(lat,lon);
    }

    public void requestDeviceLocation() {

        gpsDevice.getDeviceLocation();

    }



//Interface to pass the Network Response to UI

    public interface MainFragmentPresenterInterface{
    public void sendOpenNotifyAPIResponseData(List<Response> responseList);

    public void sendOpenNotifyAPIFailureResponseData(String onFailureResponse);
    }
}
