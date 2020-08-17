package com.theonetech.android.data.connection.callback;

import okhttp3.ResponseBody;

public interface SuccessCallback<T> {

    /*Success call method of Retrofit Api call*/
    void onSuccess(ResponseBody response);
}
