package com.usermindarchive.h.internationalspacestationpasses.View;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.Response;
import com.usermindarchive.h.internationalspacestationpasses.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * OpenNotifyDataViewAdapter is the RecyclerViewAdapter.
 * It will set the Layout file for the View in RecyclerView.
 * Gives the values to views from the data received from Network response.
 */

public class OpenNotifyDataViewAdapter extends RecyclerView.Adapter<OpenNotifyDataViewHolder> {

    private final List<Response> responseList;

    // Getting the Data from the Network Response.
    public OpenNotifyDataViewAdapter(List<Response> responseList) {

        this.responseList=responseList;
    }

    // Inflating the Layout for the RecyclerView
    @Override
    public OpenNotifyDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.datalayout,parent,false);
        return new OpenNotifyDataViewHolder(view);
    }

    // Setting the values to Views in the Layout of Recycler View
    @Override
    public void onBindViewHolder(OpenNotifyDataViewHolder holder, int position) {

        if(position==0){

            // Giving tiles for the Open Notify API Network Response
            holder.time.setText("Time and Date");

            holder.duration.setText("Duration");

        }else {
            position-=1;
            Log.e("###", "size " + responseList.get(position).getDuration() + "/n" + responseList.get(position).getRisetime() +
                    new Date(responseList.get(position).getRisetime()));




            holder.time.setText(getFormattedDate(responseList.get(position).getRisetime()));

            holder.duration.setText(responseList.get(position).getDuration().toString());


        }

    }


    // Method to return the Date in Desired format
    private String getFormattedDate(Integer format) {
        Date date = new Date(format * 1000L);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        Log.e("time",dateFormat.format(date));
        return dateFormat.format(date);
    }

    // Passing the Required number of views for the RecyclerView
    @Override
    public int getItemCount() {
        return responseList.size()+1;
    }
}
