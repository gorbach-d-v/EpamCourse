package ru.gorbach.hw16.common.solutions.repo;

import java.util.Collection;
import java.util.List;

public interface BaseRepo<T, ID> {

    void add(T t);

    //void add(Collection<T> t);

    void update(T t);

    T findById(ID id);

    void deleteById(ID id);

    void printAll();

    List<T> findAll();

    int countAll();
}
