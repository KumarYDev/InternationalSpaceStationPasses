package com.usermindarchive.h.internationalspacestationpasses;

import android.util.EventLog;

import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyDataParser;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyEvent;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.OpenNotify;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.Response;
import com.usermindarchive.h.internationalspacestationpasses.Presenter.MainFragmentPresenter;
import com.usermindarchive.h.internationalspacestationpasses.Utils.GPSDevice.GPSDeviceEvent;

import junit.framework.Assert;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;

/**
 * MainFragmentPresenterTest is used to test the method in MainFragmentPresenter.
 */


@RunWith(RobolectricTestRunner.class)
public class MainFragmentPresenterTest {

    @Mock
    MainFragmentPresenter.MainFragmentPresenterInterface mainFragmentPresenterInterface;

    @Captor
    private ArgumentCaptor<List<Response>> captor;

    MainFragmentPresenter mainFragmentPresenter;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);
        mainFragmentPresenter = new MainFragmentPresenter(mainFragmentPresenterInterface);
    }

    //Method to test whether the MainFragmentPresenter required parameters or not
    @Test
    public void testThatPresenterIsNotNull(){
        Assert.assertNotNull(mainFragmentPresenterInterface);
        Assert.assertNotNull(mainFragmentPresenter);
    }

//Method to test the Open Notify API cal is success or failure
    @Test
    public void testThatEventBusonOpenNotifyEventIsSendOnSuccesulPost(){
        mainFragmentPresenter.onStart();

    Response res = new Response();
        res.setDuration(414);
        List<Response> response = new ArrayList();
        response.add(res);
        OpenNotifyEvent openNotify = new OpenNotifyEvent(response);

        EventBus.getDefault().post(openNotify);
        mainFragmentPresenter.onStop();
        verify(mainFragmentPresenterInterface).sendOpenNotifyAPIResponseData(anyList());
        verify(mainFragmentPresenterInterface).sendOpenNotifyAPIResponseData(captor.capture());

        Assert.assertEquals("414",captor.getValue().get(0).getDuration().toString());


    }

}
