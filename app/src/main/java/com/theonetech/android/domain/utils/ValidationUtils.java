package com.theonetech.android.domain.utils;

import java.util.regex.Pattern;

/**
 * Created by bhoomika prajapati on 8/12/20.
 */
public class ValidationUtils {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\.[a-z]+");

    public static boolean isValidEmail(String email) {
        //return !email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches();
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isMinLength(String input, Integer minLength) {
        return  input.trim().length() < minLength;
    }

    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty() || input.length() <= 0;
    }

}
