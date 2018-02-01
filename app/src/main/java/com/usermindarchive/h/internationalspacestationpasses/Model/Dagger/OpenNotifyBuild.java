package com.usermindarchive.h.internationalspacestationpasses.Model.Dagger;

import android.app.Application;

/**
 * OpenNotifyBuild class will build the Dagger Component(OpenNotifyComponent) so that it will be easy to inject in the required classes
 */

public class OpenNotifyBuild extends Application {

    private OpenNotifyComponent openNotifyComponent;
    private static OpenNotifyBuild openNotifyBuild;

    @Override
    public void onCreate() {
        super.onCreate();

//        Instance of the OpenNotifyBuild class
        openNotifyBuild=this;

//        Building the Dagger Component
        openNotifyComponent=DaggerOpenNotifyComponent.builder()
                .openNotifyModule(new OpenNotifyModule(this))
                .build();
    }

    // Getter for the Instance of the OpenNotifyBuild

    public static OpenNotifyBuild getOpenNotifyBuild(){
        return openNotifyBuild;
    }


    //    Getter for the OpenNotifyComponent

    public OpenNotifyComponent getOpenNotifyComponent(){
        return openNotifyComponent;
    }
}
