package com.theonetech.android.data.connection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.theonetech.android.R;
import com.theonetech.android.domain.utils.ProgressDialogUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


abstract class RetrofitCallback<T> implements Callback<T> {

    /**
     * Retrofit callback custom class,
     * onSuccess and onFailure retrofit Override method
     * */

    private Context mContext;
    private ProgressDialog mProgressDialog;

    public RetrofitCallback(Context c) {
        mContext = c;
    }

    public RetrofitCallback(Context c, boolean showProgressDialog) {
        mContext = c;

        if (showProgressDialog && mContext instanceof Activity) {
            mProgressDialog = ProgressDialogUtils.getInstance().showProgressDialog(mContext);
        }
    }

    public abstract void onSuccess(T response);

    public abstract void onFailure(String errorMessage);

    @Override
    public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {

        if (mContext != null) {
            if (mContext instanceof Activity && !(((Activity) mContext).isFinishing())) {
                ProgressDialogUtils.getInstance().dismissProgress(mProgressDialog);
            }
        }

        if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
            onSuccess(response.body());
        } else {
            onFailure(ResponseUtils.getErrorMessage(response.errorBody()));
        }
    }

    @Override
    public void onFailure(@NotNull Call<T> call, Throwable error) {
        String errorMsg;
        error.printStackTrace();
        if (error instanceof SocketTimeoutException) {
            errorMsg = mContext.getString(R.string.str_connection_timeout);
        } else if (error instanceof NoConnectivityException) {
            errorMsg = mContext.getString(R.string.str_no_internet);
        }
        else if (error instanceof ConnectException) {
            errorMsg = mContext.getString(R.string.str_server_not_responding);
        } else if (error instanceof JSONException || error instanceof JsonSyntaxException) {
            errorMsg = mContext.getString(R.string.str_parse_error);
        } else if (error instanceof IOException) {
            errorMsg = error.getMessage();
        } else {
            errorMsg = mContext.getString(R.string.str_something_wrong);
        }

        if (mContext != null && mContext instanceof Activity && !(((Activity) mContext).isFinishing())) {
            ProgressDialogUtils.getInstance().dismissProgress(mProgressDialog);
        }
        onFailure(errorMsg);
    }
}
