package com.usermindarchive.h.internationalspacestationpasses;

import android.content.Context;

import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyData;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyDataParser;
import com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO.OpenNotify;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * OpenNotifyDataParserTest is used to test whether the network call for Open Notify API is success or not.
 *
 * Code is Not Yet Working.
 */
@RunWith(RobolectricTestRunner.class)
public class OpenNotifyDataParserTest {

private OpenNotifyDataParser openNotifyDataParser;
    private MockWebServer mockWebServer;


    @Captor
    private ArgumentCaptor<OpenNotify> captor;
    @Mock
    EventBus eventBus;
    @Before
    public void setUp() throws Exception{
openNotifyDataParser = new OpenNotifyDataParser();
        mockWebServer = new MockWebServer();

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testThatEventBusIsPostedOnSuccessfulNetworkCall() throws Exception {
      mockWebServer.start();
     // mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(getStringFromFile(RuntimeEnvironment.application,"success.json")));
       mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
               "{\n" +
                       "  \"message\": \"success\", \n" +
                       "  \"request\": {\n" +
                       "    \"altitude\": 100, \n" +
                       "    \"datetime\": 1517423671, \n" +
                       "    \"latitude\": 39.01, \n" +
                       "    \"longitude\": -76.89, \n" +
                       "    \"passes\": 5\n" +
                       "  }, \n" +
                       "  \"response\": [\n" +
                       "    {\n" +
                       "      \"duration\": 414, \n" +
                       "      \"risetime\": 1517427760\n" +
                       "    }, \n" +
                       "    {\n" +
                       "      \"duration\": 637, \n" +
                       "      \"risetime\": 1517433401\n" +
                       "    }, \n" +
                       "    {\n" +
                       "      \"duration\": 589, \n" +
                       "      \"risetime\": 1517439230\n" +
                       "    }, \n" +
                       "    {\n" +
                       "      \"duration\": 507, \n" +
                       "      \"risetime\": 1517445122\n" +
                       "    }, \n" +
                       "    {\n" +
                       "      \"duration\": 555, \n" +
                       "      \"risetime\": 1517450957\n" +
                       "    }\n" +
                       "  ]\n" +
                       "}\n"
       ));
        OpenNotifyData.Base_url = mockWebServer.url("/").toString();
        openNotifyDataParser.getJSONDataFromAPI(39.010,-76.89);

    verify(eventBus).post(captor.capture());
        OpenNotify openNotify1 = captor.getValue();
        assertThat("414",equals(openNotify1.getResponse().get(0).getDuration()));


    }


    private String getStringFromFile(Context context, String filePath) throws Exception {
        final InputStream stream = RuntimeEnvironment.application.getAssets().open(filePath);

        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }

    private String convertStreamToString(InputStream is) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {

            sb.append(line).append("\n");

        }

        reader.close();

        return sb.toString();

    }
}
