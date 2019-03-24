package ru.gorbach.hw14.common.solutions.paginationutils;

import java.util.Collections;
import java.util.List;

public class PaginationUtils {
    private PaginationUtils() {
    }

    public static <T> List<T> getLimitList(List<T> values, Pagination pagination) {
        if (pagination == null) {
            return values;
        } else {
            if (pagination.getOffset() > values.size() - 1) {
                return Collections.emptyList();
            }
            int upperValue = pagination.getOffset() + pagination.getLimit();
            return upperValue < values.size() ?
                    values.subList(pagination.getOffset(), upperValue) :
                    values.subList(pagination.getOffset(), values.size());
        }
    }
}
