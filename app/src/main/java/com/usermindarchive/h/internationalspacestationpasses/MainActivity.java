package com.usermindarchive.h.internationalspacestationpasses;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.usermindarchive.h.internationalspacestationpasses.Model.Dagger.OpenNotifyBuild;
import com.usermindarchive.h.internationalspacestationpasses.Utils.InternetConnectionCheck.InternetCheck;
import com.usermindarchive.h.internationalspacestationpasses.Utils.InternetConnectionCheck.InternetCheckEvent;
import com.usermindarchive.h.internationalspacestationpasses.View.MainFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;
/**
 * MainActivity is the Launch Activity of the application.
 */
public class MainActivity extends AppCompatActivity {

    //    Adding the Views of the Layout
    MainFragment mainFragment;
    TextView internetStatus;

    //    Injecting the InternetCheck provided by Dagger Module
    @Inject InternetCheck internetCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internetStatus=(TextView) findViewById(R.id.nointernet);

        // Registering for the EventBus Events
        EventBus.getDefault().register(this);
        permissionsCheck();

        // Injecting the Dagger Component so that the Dagger provided values can be used.
        OpenNotifyBuild.getOpenNotifyBuild().getOpenNotifyComponent().inject(this);

        // Triggering the InternetCheck Event to get the Status of Internet
        internetCheck.getInternetConnectionStatus();

    }

    // Registered Event method for InternetCheckEvent
    // Internet Connection Status Response Event
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInternetCheckEvent(InternetCheckEvent event) {

        //Updating UI depending on the InternetCheck response
        if(event.getConnectionStatus()){
            //        If device have Internet

            permissionsCheck();
            internetStatus.setVisibility(View.GONE);

            // Adding the MainFragment to Fragment Container to Display Response
//            setMainFragment();
        }else{
            // If device does't have Internet
            clearMainFragment();
            // Displaying Static Message
            internetStatus.setVisibility(View.VISIBLE);
            internetStatus.setText(getResources().getString(R.string.internetstatus));
        }

    }

    // Method to clear the Fragment Container if any fragment is present
    private void clearMainFragment() {
        if(mainFragment!=null){
            getSupportFragmentManager().beginTransaction().remove(mainFragment).commit();
        }
    }

    // Removing the Registered Events
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    // Method to add the MainFragment to Fragment Container to Display Response.
    public void setMainFragment(){
        mainFragment=new MainFragment();
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainfragment,mainFragment);
        fragmentTransaction.commit();

    }

    // Inflating the Menu Layout to Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Giving the function to views of the menu lay out
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            // Refreshing the UI with Updated Network Response
            case R.id.refresh:
                internetCheck.getInternetConnectionStatus();
                return true;

            default:
                break;
        }

        return false;
    }



    public void permissionsCheck(){

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}
                        ,6);
            }

        }else {
            setMainFragment();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 6 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Triggering the InternetCheck Event to get the Status of Internet
            internetCheck.getInternetConnectionStatus();

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    internetStatus.setVisibility(View.VISIBLE);
                    internetStatus.setText(getResources().getString(R.string.permissionStatus));
                } else {
                   showPermmissionAlert();

                }
            }
        }




/*
        if (requestCode == 6) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
//                        &&
//                        shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
                        ) {
                    showPermmissionAlert();
                }
            }


                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){


                }else {


                }
    }*/
    }

    private void showPermmissionAlert() {
        AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
        dia.setMessage(getResources().getString(R.string.permissionMessage));
        dia.setTitle(getResources().getString(R.string.permissionTile));
        dia.setPositiveButton(getResources().getString(R.string.permissionPositiveButtonTile), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        dia.setNegativeButton(getResources().getString(R.string.permissionNegitiveButtonTile), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                internetStatus.setVisibility(View.VISIBLE);
                internetStatus.setText(getResources().getString(R.string.permissionStatus));
                Toast.makeText(MainActivity.this,getResources().getString( R.string.permissionToastMessage), Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = dia.create();
        dialog.show();
    }
}
