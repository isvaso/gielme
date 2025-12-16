package com.isvaso.util;

public class StringValidator {

    public static boolean isEmptyOrNull(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isBlankOrNull(String string) {
        return string == null || string.isBlank();
    }
}
