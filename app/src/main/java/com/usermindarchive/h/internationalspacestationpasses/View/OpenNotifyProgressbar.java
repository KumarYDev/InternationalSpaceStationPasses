package com.usermindarchive.h.internationalspacestationpasses.View;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by HERO on 1/31/2018.
 */

public class OpenNotifyProgressbar {




    private final ProgressDialog openNotifyProgressBar;

    public OpenNotifyProgressbar(Context context) {
        super();
        openNotifyProgressBar = new ProgressDialog(context);
        openNotifyProgressBar.setMessage("Loading..."); // Setting Message
        openNotifyProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner

//        openNotifyProgressBar.setCancelable(false);
    }

    public ProgressDialog getOpenNotifyProgressBar() {
        return openNotifyProgressBar;
    }
}
