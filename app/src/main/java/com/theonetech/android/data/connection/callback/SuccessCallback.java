package com.theonetech.android.data.connection.callback;

import okhttp3.ResponseBody;

public interface SuccessCallback<T> {
    void onSuccess(ResponseBody response);
}
