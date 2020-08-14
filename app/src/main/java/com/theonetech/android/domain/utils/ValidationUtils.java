package com.theonetech.android.domain.utils;

import android.util.Patterns;

/**
 * Created by bhoomika prajapati on 8/12/20.
 */
public class ValidationUtils {


    public static boolean isValidEmail(String email) {
        return !email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isMinLength(String input, Integer minLength) {
        return  input.trim().length() < minLength;
    }

    public static boolean isBlank(String input) {
        return input.trim().isEmpty() || input.length() <= 0;
    }


}
