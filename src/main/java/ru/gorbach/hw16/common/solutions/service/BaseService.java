package ru.gorbach.hw16.common.solutions.service;

import ru.gorbach.hw16.common.business.exception.ReservationUncheckedException;

import java.util.Collection;
import java.util.List;

public interface BaseService<T, ID> {
    void add(T t);

    void add(Collection<T> t);

    void update(T t);

    T findById(ID id);

    void deleteById(ID id) throws ReservationUncheckedException;

    void delete(T t);

    void delete(Collection<T> t);

    void printAll();

    List<T> findAll();

    int countAll();
}
