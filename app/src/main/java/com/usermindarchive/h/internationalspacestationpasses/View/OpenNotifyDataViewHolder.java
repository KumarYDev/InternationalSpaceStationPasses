package com.usermindarchive.h.internationalspacestationpasses.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.usermindarchive.h.internationalspacestationpasses.R;

/**
 * OpenNotifyDataViewHolder is the RecyclerViewViewHolder.
 * It will contain the  views of Layout file used in RecyclerView.
 */

public class OpenNotifyDataViewHolder extends RecyclerView.ViewHolder {
    TextView duration,time;

    public OpenNotifyDataViewHolder(View itemView) {
        super(itemView);
        duration=itemView.findViewById(R.id.duration);
        time=itemView.findViewById(R.id.time);
    }
}
