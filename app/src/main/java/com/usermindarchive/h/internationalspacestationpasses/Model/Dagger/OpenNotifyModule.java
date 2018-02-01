package com.usermindarchive.h.internationalspacestationpasses.Model.Dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import com.usermindarchive.h.internationalspacestationpasses.MainActivity;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.OpenNotify;
import com.usermindarchive.h.internationalspacestationpasses.Utils.GPSDevice.GPSDevice;
import com.usermindarchive.h.internationalspacestationpasses.Utils.InternetConnectionCheck.InternetCheck;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyData;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyDataParser;
import com.usermindarchive.h.internationalspacestationpasses.View.MainFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * OpenNotifyModule is the  Dagger Module class
 * It contains all the Provide methods that will be Injected through Dagger Component
 */
@Module
public class OpenNotifyModule {


    private final Context context;

    public OpenNotifyModule(Context context) {
        this.context=context;
    }



    //    Method to get Context of the Application
    @Singleton
    @Provides
    Context provideContext(){
        return context;
    }

    //    Method to get Retrofit instance from the OpenNotifyData class
    @Singleton
    @Provides
    @Named("Real")
    Retrofit providesRetrofitAdapter(){
        return new OpenNotifyData().getRetrofitAdapter();
    }

    @Singleton
    @Provides
    @Named("Dummy")
    Retrofit provideRetrofitForTesting() {
        return new OpenNotifyData().getRetrofitForTesting();
    }


    //    Method to get the instance of the OpenNotifyDataParser class
    /*@Singleton
    @Provides
    OpenNotifyDataParser providesOpenNotifyDataParser(Retrofit retrofit){
        return new OpenNotifyDataParser(retrofit);
    }*/

    @Singleton
    @Provides
    OpenNotifyDataParser providesOpenNotifyDataParser(){
        return new OpenNotifyDataParser();
    }

    //    Method to get the instance of the InternetCheck class
    @Singleton
    @Provides
    InternetCheck providesInternetCheck(Context context){
        return new InternetCheck(context);
    }

    //    Method to get the instance of the GPSDevice class
    @Singleton
    @Provides
    GPSDevice providesGPSDevice(Context context){
        return new GPSDevice(context);
    }


}
