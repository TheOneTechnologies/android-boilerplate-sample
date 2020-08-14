package com.theonetech.android.data.connection;

import com.theonetech.android.R;
import com.theonetech.android.domain.application.GlobalApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ResponseUtils {
    /**
     * to get error message from unsuccessful response of api call
     **/
    public static String getErrorMessage(ResponseBody responseBody) {
        // change below according to your response
        if (responseBody == null) {
            return GlobalApplication.getAppContext().getString(R.string.str_something_wrong);
        }
        try {
            JSONObject errorObject = new JSONObject(responseBody.string());
            return errorObject.getJSONArray("errors").getJSONObject(0).getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GlobalApplication.getAppContext().getString(R.string.str_something_wrong);
    }
}
