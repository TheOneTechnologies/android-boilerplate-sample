package com.theonetech.android.presentation.view.activity;

import com.google.gson.JsonObject;
import com.theonetech.android.R;
import com.theonetech.android.data.connection.ApiConstant;
import com.theonetech.android.data.connection.ApiInterface;
import com.theonetech.android.data.connection.ApiServiceClient;
import com.theonetech.android.data.connection.NetworkConnectionInterceptor;
import com.theonetech.android.domain.application.GlobalApplication;
import com.theonetech.android.domain.utils.Const;
import com.theonetech.android.presentation.utils.FileReader;

import org.hamcrest.core.AllOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;

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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private MockWebServer mMockWebServer;
    private ApiInterface mApiService;

    //SetUp MockWebServer And OkHttpClient for check Retrofit Api
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

    @After
    public void teardown() {
        try {
            mMockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Success response for Api
    @Test
    public void testSuccessfulResponse() {
        //Assign response to mock response
        MockResponse response = new MockResponse();
        response.setResponseCode(HttpURLConnection.HTTP_OK);
        response.setBody(FileReader.readStringFromFile("user_login.json"));
        mMockWebServer.enqueue(response);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Const.USERNAME, "Student11_1A");
        jsonObject.addProperty(Const.PASSWORD, "Password12345");
        jsonObject.addProperty(Const.SCHOOL_ID, 1);
        jsonObject.addProperty(Const.IS_STUDENT, true);

        Response<Object> apiResponse = null;
        try {
            apiResponse = mApiService.postAPICall(ApiConstant.LOGIN, jsonObject).execute();
            assertEquals(apiResponse.code(), HttpURLConnection.HTTP_OK);
            assertTrue(apiResponse.isSuccessful());
            System.out.println("Login response->" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Failure response of api
    @Test
    public void testFailedResponse() {
        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().equals(ApiConstant.LOGIN)) {
                    return new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                            .setBody(FileReader.readStringFromFile("user_login.json"));
                }
                throw new IllegalStateException("no mock set up for " + request.getPath());
            }
        };

        mMockWebServer.setDispatcher(dispatcher);
    }


    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    //Check view is visible
    @Test
    public void view_IsVisible() {
        onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_password)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_register)).check(matches(isDisplayed()));
    }

    //Check Login using static data
    @Test
    public void isLogin() {
        onView(withId(R.id.edt_username)).perform(typeText("Student11_1A"), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText("Password12345"), closeSoftKeyboard());
        onView(AllOf.allOf(withId(R.id.btn_login), withText("Login"))).perform(click());

        //Wait for a constant time of 2 seconds to get the response from server for login
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(mActivityRule.getActivity().isFinishing());
    }
}
