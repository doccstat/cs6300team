package edu.gatech.seclass.crypto6300.utils;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;

public class PlayerUtils {

    public static boolean isValidFirstnameLength(@NonNull String firstname) {
        return (firstname.length() >= 3 && firstname.length() < 25);
    }

    public static boolean isValidLastnameLength(@NonNull String lastname) {
        return (lastname.length() >= 3 && lastname.length() < 25);
    }

    public static boolean isValidUsernameLength(@NonNull String username) {
        return (username.length() >= 3 && username.length() < 25);
    }

    public static boolean isValidPasswordLength(@NonNull String password) {
        return (password.length() >= 3 && password.length() < 25);
    }

    public static boolean isValidLettersNumAndUnderscore(@NonNull String input) {
        return !input.isEmpty() && Pattern.matches("[a-zA-Z0-9_]+", input);
    }

    public static boolean isLettersOnly(@NonNull String input) {
        return !input.isEmpty() && Pattern.matches("[a-zA-Z]+", input);
    }
}
