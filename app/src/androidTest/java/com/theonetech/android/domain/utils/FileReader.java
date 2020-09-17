package com.theonetech.android.domain.utils;

import com.theonetech.android.domain.application.GlobalApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileReader {

    //FileReader class is used to read data from the file.
    public static String readStringFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(GlobalApplication.getAppContext().getApplicationContext().getAssets().open(fileName), "UTF-8"));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                builder.append(mLine);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
