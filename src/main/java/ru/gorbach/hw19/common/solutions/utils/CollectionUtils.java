package ru.gorbach.hw19.common.solutions.utils;

import java.util.List;

public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static <T> List<T> getPageOfData(List<T> list, final int limit, final int offset) {
        if (offset >= list.size()) {
            return list;
        } else {
            int upperLimit = (offset + limit > list.size()) ? list.size() : offset + limit;
            return list.subList(offset, upperLimit);
        }
    }
}
