package com.theonetech.android.data.connection;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.theonetech.android.domain.application.GlobalApplication;
import com.theonetech.android.domain.model.Albums;
import com.theonetech.android.domain.model.HomeWorks;
import com.theonetech.android.domain.model.LoginResponse;
import com.theonetech.android.domain.utils.Const;
import com.theonetech.android.domain.utils.SharedPrefUtils;
import com.theonetech.android.domain.utils.Utils;

import java.util.HashMap;

import retrofit2.Call;

public class ApiInteractor {

    /**
     * Api Interactor contains all api calls of project
     */

    private static ApiInteractor apiCall;
    private static Context mContext;
    private static String mToken;
    private static boolean mShowProgressDialog = false;
    private Gson gson;

    public ApiInteractor(Context context) {
        gson = new Gson();
        updateVariables(context);
    }

    public ApiInteractor(Context context, boolean showProgressDialog) {
        gson = new Gson();
        updateVariables(context);
        mShowProgressDialog = showProgressDialog;
    }

    private static void updateVariables(Context context) {
        mContext = context;
        mToken = SharedPrefUtils.getStringData(GlobalApplication.getAppContext(), Const.TOKEN_TYPE) + " " + SharedPrefUtils.getStringData(GlobalApplication.getAppContext(), Const.ACCESS_TOKEN);
    }

    /**
     * get class instance
     */

    public static ApiInteractor getInstance(Context context) {
        if (apiCall == null) {
            apiCall = new ApiInteractor(context);
        }


        mShowProgressDialog = false;
        updateVariables(context);
        return apiCall;
    }

    /**
     * get class instance
     */

    public static ApiInteractor getInstance(Context context, boolean showProgressDialog) {
        if (apiCall == null) {
            apiCall = new ApiInteractor(context, showProgressDialog);
        }
        mShowProgressDialog = showProgressDialog;
        updateVariables(context);
        return apiCall;
    }

    /**
     * login user api call
     */
    public void loginUser(final JsonObject jsonObject,
                          final ApiResult<LoginResponse> apiResult) {

        Call<Object> call = ApiServiceClient.getClient().create(ApiInterface.class).postAPICall(ApiConstant.LOGIN, jsonObject);
        call.enqueue(new RetrofitCallback<Object>(mContext, mShowProgressDialog) {
            @Override
            public void onSuccess(Object response) {
                apiResult.onSuccess((LoginResponse) getResponse(response, LoginResponse.class));
            }

            @Override
            public void onFailure(String errorMessage) {
                apiResult.onFailure(errorMessage);
                Utils.showToast(mContext, errorMessage);


            }
        });
    }

    /**
     * get home work list api call
     */
    public void getHomeWorkList(final HashMap<String, Object> paramsMap, ApiResult<HomeWorks> apiResult) {

        Call<Object> call = ApiServiceClient.getClient().create(ApiInterface.class).getAPICall(ApiConstant.HOMEWORK, mToken, paramsMap);
        call.enqueue(new RetrofitCallback<Object>(mContext, mShowProgressDialog) {
            @Override
            public void onSuccess(Object response) {
                apiResult.onSuccess((HomeWorks) getResponse(response, HomeWorks.class));
            }

            @Override
            public void onFailure(String errorMessage) {
                Utils.showToast(mContext, errorMessage);
            }
        });
    }

    /**
     * get home Album list api call
     */
    public void getAlbumList(final HashMap<String, Object> paramsMap, final ApiResult<Albums> apiResult) {

        Call<Object> call = ApiServiceClient.getClient().create(ApiInterface.class).getAPICall(ApiConstant.ALBUMS, mToken, paramsMap);
        call.enqueue(new RetrofitCallback<Object>(mContext, mShowProgressDialog) {
            @Override
            public void onSuccess(Object response) {
                apiResult.onSuccess((Albums) getResponse(response, Albums.class));
            }

            @Override
            public void onFailure(String errorMessage) {
                apiResult.onFailure(errorMessage);
                Utils.showToast(mContext, errorMessage);
            }
        });
    }


    /**
     * get required response model from the object
     */
    private Object getResponse(Object arg0, Class requiredClass) {
        JsonElement jsonElement = gson.toJsonTree(arg0);
        return gson.fromJson(jsonElement, requiredClass);
    }
}


