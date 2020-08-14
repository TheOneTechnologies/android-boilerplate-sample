package com.theonetech.android.data.connection;

import com.theonetech.android.domain.application.GlobalApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceClient {

    private static long TIME = 2;
    private static ApiServiceClient mInstance;

    /**
     * Get Retrofit Instance
     */
    public static Retrofit getClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        //Building the HTTPClient with the logger
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new NetworkConnectionInterceptor(GlobalApplication.getAppContext()))
                .addInterceptor(loggingInterceptor)
                .callTimeout(TIME, TimeUnit.SECONDS)
                .readTimeout(TIME, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * get retrofit instance with header params
     *
     * @return
     */
    public static Retrofit getClientWithHeader() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        //Building the HTTPClient with the logger
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new NetworkConnectionInterceptor(GlobalApplication.getAppContext()))
                .addInterceptor(loggingInterceptor)
                .callTimeout(TIME, TimeUnit.SECONDS)
                .readTimeout(TIME, TimeUnit.SECONDS);


        httpClient.addInterceptor(chain -> {
            // add your header here using key value
            Request request = chain.request().newBuilder().addHeader("key", "value").build();
            return chain.proceed(request);
        });

        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiInterface getApiService() {
        return getClient().create(ApiInterface.class);
    }


}

