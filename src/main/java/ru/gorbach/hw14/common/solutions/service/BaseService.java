package ru.gorbach.hw14.common.solutions.service;

import ru.gorbach.hw14.common.business.exception.ReservationUncheckedException;

import java.util.List;

public interface BaseService<T, ID> {
    void add(T t);

    void update(T t);

    T findById(ID id);

    void delete(T customer);

    void deleteById(ID id) throws ReservationUncheckedException;

    void printAll();

    List<T> findAll();

    int countAll();
}
