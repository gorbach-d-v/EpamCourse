package ru.gorbach.hw16.common.solutions.utils;

import java.util.List;

public class CollectionUtils {
    public static <T> List<T> getPageOfData(List<T> list, final int limit, final int offset) {
        if (offset >= list.size()) {
            return list;
        } else {
            int upperLimit = (offset + limit > list.size()) ? list.size() : offset + limit;
            return list.subList(offset, upperLimit);
        }
    }
}
