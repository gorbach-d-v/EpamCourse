package ru.gorbach.hw22.common.business.domain;

public abstract class BaseDomain<ID> {
    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
