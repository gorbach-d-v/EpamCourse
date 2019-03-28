package ru.gorbach.hw14.common.solutions.repo;

import java.util.List;

public interface BaseRepo<T, ID> {

    void add(T t);

    void update(T city);

    T findById(ID id);

    void deleteById(ID id);

    void printAll();

    List<T> findAll();

    int countAll();
}
