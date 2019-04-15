package ru.gorbach.hw22.common.business.dto;

import java.io.Serializable;

public class BaseDto<ID> implements Serializable {
    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
