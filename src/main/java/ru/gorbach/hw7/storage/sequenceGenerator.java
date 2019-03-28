package ru.gorbach.hw7.storage;

public final class sequenceGenerator {
    private static long id = 0;

    private sequenceGenerator() {
    }

    public static Long generateId(){
        return ++id;
    }
}
