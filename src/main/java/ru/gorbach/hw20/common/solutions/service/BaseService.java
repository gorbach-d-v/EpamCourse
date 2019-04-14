package ru.gorbach.hw20.common.solutions.service;

import ru.gorbach.hw20.common.business.exception.ReservationUncheckedException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    T add(T t);

    void add(Collection<T> t);

    void update(T t);

    Optional<T> findById(ID id);

    void deleteById(ID id) throws ReservationUncheckedException;

    void delete(T t);

    void delete(Collection<T> t);

    void printAll();

    List<T> findAll();

    int countAll();
}
