package ru.gorbach.hw17.common.business.search;

import ru.gorbach.hw17.common.application.ApplicationConfigurations;

public class Paginator {
    private int offset;
    private int limit = ApplicationConfigurations.PAGE_SIZE;

    public Paginator() {
    }

    public Paginator(int offset) {
        this.offset = offset;
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}
