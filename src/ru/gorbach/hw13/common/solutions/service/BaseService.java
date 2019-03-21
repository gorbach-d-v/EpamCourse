package ru.gorbach.hw13.common.solutions.service;

import ru.gorbach.hw13.common.business.exception.ReservationUncheckedException;

public interface BaseService<T, ID> {
    void add(T t);

    void update(T t);

    T findById(ID id) throws ReservationUncheckedException;

    void delete(T customer);

    void deleteById(ID id);

    void printAll();
}
