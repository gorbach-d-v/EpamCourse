package ru.gorbach.hw19.common.solutions.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseRepo<T, ID> {

    T add(T t);

    void add(Collection<T> t);

    void update(T t);

    Optional<T> findById(ID id);

    void deleteById(ID id);

    void printAll();

    List<T> findAll();

    int countAll();
}
