package ru.gorbach.hw8.common.solutions;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isBlank(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}
