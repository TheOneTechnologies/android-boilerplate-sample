package com.theonetech.android.data.connection;

import com.theonetech.android.data.connection.callback.FailureCallback;

public abstract class ApiResult<T> implements FailureCallback {

    /**
     * Custom callback retrofit api response method
     * */

    public abstract void onSuccess(T response);

    @Override
    public void onFailure(String error) {

    }
}
