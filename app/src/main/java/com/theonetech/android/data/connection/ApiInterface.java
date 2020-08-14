package com.theonetech.android.data.connection;


import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {

    /**
     * common POST api call
     *
     * @param url
     * @param jsonObject
     * @return
     */


    @POST
    Call<Object> postAPICall(@Url String url, @Body JsonObject jsonObject);


    /**
     * common GET api call
     *
     * @param url
     * @param header
     * @param paramsMap
     * @return
     */


    @GET
    Call<Object> getAPICall(@Url String url, @Header("Authorization") String header, @QueryMap(encoded = true) Map<String, Object> paramsMap);
}
