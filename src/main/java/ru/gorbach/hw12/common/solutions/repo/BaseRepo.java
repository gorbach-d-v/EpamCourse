package ru.gorbach.hw12.common.solutions.repo;

public interface BaseRepo<T, ID> {

    void add(T t);

    void update(T city);

    T findById(ID id);

    void deleteById(ID id);

    void printAll();
}
