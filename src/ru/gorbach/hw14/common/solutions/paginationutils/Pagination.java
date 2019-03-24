package ru.gorbach.hw14.common.solutions.paginationutils;

public class Pagination {
    private int offset;
    private int limit;

    public Pagination(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

}
