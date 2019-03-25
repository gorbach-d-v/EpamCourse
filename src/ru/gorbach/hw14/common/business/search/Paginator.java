package ru.gorbach.hw14.common.business.search;

public class Paginator {
    private int offset;
    private int limit;

    public Paginator(int offset, int limit) {
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
