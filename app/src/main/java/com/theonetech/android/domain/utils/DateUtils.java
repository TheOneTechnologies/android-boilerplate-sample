package com.theonetech.android.domain.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    @SuppressLint("SimpleDateFormat")
    public static String parseDateToddMMYY(String time) {
        String dateStr = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:ss");
            Date date = dateFormat.parse(time);
            SimpleDateFormat formatter = new SimpleDateFormat("MMM-yy");
            dateStr = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDayFromString(String strDate) {
        String dateStr = null;
        try {
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:ss");
            Date date = dateFormat.parse(strDate);
            SimpleDateFormat formatter = new SimpleDateFormat("dd");
            dateStr = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;

    }
}
