package ru.gorbach.hw9.common.solutions.utils;


public class ArrayUtils {
    private ArrayUtils() {
    }

    public static void removeElement(Object[] arr, int index) {
        System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
    }
}
