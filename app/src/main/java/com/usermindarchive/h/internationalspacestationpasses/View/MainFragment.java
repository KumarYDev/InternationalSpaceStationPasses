package com.usermindarchive.h.internationalspacestationpasses.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.usermindarchive.h.internationalspacestationpasses.MainActivity;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.Response;
import com.usermindarchive.h.internationalspacestationpasses.Presenter.MainFragmentPresenter;
import com.usermindarchive.h.internationalspacestationpasses.R;

import java.util.List;

/**
 * MainFragment is the view which will be Displaying the Open Notify API Network response.
 */

public class MainFragment extends Fragment implements MainFragmentPresenter.MainFragmentPresenterInterface{

    private MainFragmentPresenter mainFragmentPresenter;
    private Double lat=39.010,lon=-76.89;
    private RecyclerView dataPresenter;
    private OpenNotifyDataViewAdapter openNotifyDataViewAdapter;
    private Context context;
    private TextView status;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mainfragment,container,false);
        dataPresenter=(RecyclerView) view.findViewById(R.id.datapresenter);
        status=(TextView)view.findViewById(R.id.status);

        // Instance of the Presenter
        mainFragmentPresenter=new MainFragmentPresenter(this);


        openNotifyAPICall();
        return view;
    }

    private void openNotifyAPICall() {
        // Triggering the Network Call.
//        makeOpenNotifyAPIcall.makeNetworkcall(lat,lon);
        mainFragmentPresenter.requestDeviceLocation();
        status.setText(getResources().getString(R.string.loadingmessage));
        dataPresenter.setVisibility(View.GONE);
        status.setVisibility(View.VISIBLE);

    }


    //  This Method will give presenter OnStart feature of the Fragment
    //  To Support te EventBus feature.
    @Override
    public void onStart() {
        super.onStart();
        mainFragmentPresenter.onStart();
    }

    //This Method will give presenter OnStop feature of the Fragment
    //  To Support te EventBus feature.
    @Override
    public void onStop() {
        super.onStop();
        mainFragmentPresenter.onStop();
    }


    // Interface method to receive the Network Response
    @Override
    public void sendOpenNotifyAPIResponseData(List<Response> responseList) {

        Log.e("###","size "+ responseList.size());

        //Passing the Network Response List to RecyclerViewAdapter
        openNotifyDataViewAdapter=new OpenNotifyDataViewAdapter(responseList);

        //Setting the Layout to RecyclerView
        dataPresenter.setLayoutManager(new LinearLayoutManager(context));


        // Setting the Adapter to RecyclerView
        dataPresenter.setAdapter(openNotifyDataViewAdapter);

        status.setVisibility(View.GONE);
        dataPresenter.setVisibility(View.VISIBLE);




    }

    @Override
    public void sendOpenNotifyAPIFailureResponseData(String onFailureResponse) {
        dataPresenter.setVisibility(View.GONE);
        status.setVisibility(View.VISIBLE);
        status.setText(onFailureResponse);
    }


}
