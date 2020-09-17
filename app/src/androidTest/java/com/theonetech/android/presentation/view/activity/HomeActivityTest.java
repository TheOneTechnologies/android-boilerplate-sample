package com.theonetech.android.presentation.view.activity;

import com.theonetech.android.R;
import com.theonetech.android.data.connection.ApiConstant;
import com.theonetech.android.data.connection.ApiInterface;
import com.theonetech.android.data.connection.ApiServiceClient;
import com.theonetech.android.data.connection.NetworkConnectionInterceptor;
import com.theonetech.android.domain.application.GlobalApplication;
import com.theonetech.android.domain.utils.Const;
import com.theonetech.android.domain.utils.FileReader;
import com.theonetech.android.presentation.view.fragment.ListFragment;
import com.theonetech.android.presentation.view.fragment.PaginationListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import androidx.fragment.app.FragmentTransaction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(HomeActivity.class);

    private MockWebServer mMockWebServer;
    private ApiInterface mApiService;


    @Test
    public void listFragment_can_be_instantiated() {
        mActivityRule.getActivity().runOnUiThread(() -> {
            ListFragment listFragment = startListFragment();

        });
        // Then use Espresso to test the Fragment
        onView(withId(R.id.rvList)).check(matches(isDisplayed()));
    }


    @Test
    public void paginationListFragment_can_be_instantiated() {
        mActivityRule.getActivity().runOnUiThread(() -> {

            PaginationListFragment paginationFragment = startPaginationFragment();
        });
        // Then use Espresso to test the Fragment
        onView(withId(R.id.rvList)).check(matches(isDisplayed()));
    }

    //transaction of list Fragment
    private ListFragment startListFragment() {
        HomeActivity activity = (HomeActivity) mActivityRule.getActivity();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        ListFragment listFragment = new ListFragment();
        transaction.add(listFragment, "");
        transaction.commit();
        return listFragment;
    }

    //transaction of  pagination with list fragment
    private PaginationListFragment startPaginationFragment() {
        HomeActivity activity = (HomeActivity) mActivityRule.getActivity();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        PaginationListFragment paginationListFragment = new PaginationListFragment();
        transaction.add(paginationListFragment, "");
        transaction.commit();
        return paginationListFragment;
    }


    //SetUp MockWebServer
    @Before
    public void setup() {
        try {
            mMockWebServer = new MockWebServer();
            mMockWebServer.start();
            mApiService = ApiServiceClient.getApiService();

            //Building the HTTPClient with the logger
            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new NetworkConnectionInterceptor(GlobalApplication.getAppContext()));

            mApiService = new Retrofit.Builder()
                    .baseUrl(mMockWebServer.url(ApiConstant.BASE_URL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                    .create(ApiInterface.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //SutDown MockWebServer
    @After
    public void teardown() {
        try {
            mMockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSuccessfulResponse() {
        //Assign response to mock response
        MockResponse response = new MockResponse();
        response.setResponseCode(HttpURLConnection.HTTP_OK);
        response.setBody(FileReader.readStringFromFile("list_image.json"));
        mMockWebServer.enqueue(response);

        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Const.PAGE_NUMBER, 1);
        paramsMap.put(Const.PAGE_SIZE, 20);

        String mToken = "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMTNlMmQ0NC00ZDE2LTQ0N2UtYTkwNy1iYTEwOWY3MjY2MWEiLCJqdGkiOiIyMDY2ZmEwYy0zN2I2LTRhYzQtYTk5MS1hMGFiYmQ0YWIyMTgiLCJpYXQiOjE1OTc0MTQ2ODgsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiJzdHVkZW50MTFfMUEiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9naXZlbm5hbWUiOiJTdHVkZW50IFNjaG9vbCAxXzFBIiwiU0NIX0lEIjoiMSIsIlNURF9JRCI6IjEiLCJESVZfSUQiOiIzIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiU3R1ZGVudCIsIm5iZiI6MTU5NzQxNDY4OCwiZXhwIjoxNTk3NTAxMDg4LCJpc3MiOiJQb3J0YWxBdXRoZW50aWNhdGlvblNlcnZpY2UiLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjUwMDEvIn0.swQ39yDNlptQD1SMDjNG_kLr6ITu7ksO4VZ9CL9Dzl8";

        Response<Object> apiResponse = null;
        try {
            apiResponse = mApiService.getAPICall(ApiConstant.ALBUMS, mToken, paramsMap).execute();
            assertEquals(apiResponse.code(), HttpURLConnection.HTTP_OK);
            assertTrue(apiResponse.isSuccessful());
            System.out.println("Album response->" + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFailedResponse() {
        //Check API call is failed
        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                if (request.getPath().equals(ApiConstant.ALBUMS)) {
                    return new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                            .setBody(FileReader.readStringFromFile("list_image.json"));
                }
                throw new IllegalStateException(" " + request.getPath());
            }
        };

        mMockWebServer.setDispatcher(dispatcher);
    }

}
