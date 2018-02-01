package com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit;

import android.support.annotation.NonNull;

import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.OpenNotify;
import com.usermindarchive.h.internationalspacestationpasses.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * OpenNotifyData is responsible for building the Retrofit with the static part of URL.
 */

public class OpenNotifyData {
    private Retrofit retrofit;
//    Static part of URL to make the network call.
    public static String Base_url="http://api.open-notify.org/";


//  Method to build and return the Retrofit instance.
    public Retrofit getRetrofitAdapter(){

        // Used to log the network response
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        // Building the Retrofit Instance
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


    public Retrofit getRetrofitForTesting(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient();
      //  httpClient.interceptors().add(logging);
        Dispatcher dispatcher = new Dispatcher(newSynchronousExecutorService());
        httpClient.newBuilder().dispatcher(dispatcher);

        retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static ExecutorService newSynchronousExecutorService(){
        return new AbstractExecutorService() {

            private boolean shutingDown = false;
            //
            private boolean terminated = false;
            //
            @Override
            public void shutdown() {
                this.shutingDown = true;
//
                this.terminated = true;
            }

            @NonNull
            @Override
            public List<Runnable> shutdownNow() {
                return new ArrayList<>();
            }

            @Override
            public boolean isShutdown() {
                return this.shutingDown;
            }

            @Override
            public boolean isTerminated() {
                return this.terminated;
            }

            @Override
            public boolean awaitTermination(long l, @NonNull TimeUnit timeUnit) throws InterruptedException {
                return false;
            }

            @Override
            public void execute(@NonNull Runnable runnable) {
                runnable.run();
            }
        }  ;
    }

}
