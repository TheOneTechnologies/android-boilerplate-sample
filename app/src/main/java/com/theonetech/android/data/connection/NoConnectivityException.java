package com.theonetech.android.data.connection;

import java.io.IOException;

public class NoConnectivityException extends IOException {
    /**
     * Network Connectivity Exception
     * */

    @Override
    public String getMessage() {
        return "No internet connection available. Please try again later.";
            // You can send any message whatever you want from here.
    }
}
