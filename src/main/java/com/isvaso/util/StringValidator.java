package com.isvaso.util;

public class StringValidator {

    public static boolean isEmpty(String string) {
        if (string == null)
            return false;
        return string.isEmpty();
    }

    public static boolean isEmptyOrNull(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isBlankOrNull(String string) {
        return string == null || string.isBlank();
    }
}
