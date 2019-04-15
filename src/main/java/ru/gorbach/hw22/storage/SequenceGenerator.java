package ru.gorbach.hw22.storage;

public final class SequenceGenerator {
    private static long id = 0;

    private SequenceGenerator() {
    }

    public static Long generateId() {
        return ++id;
    }
}
