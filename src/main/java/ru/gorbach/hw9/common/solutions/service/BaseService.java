package ru.gorbach.hw9.common.solutions.service;

public interface BaseService <T,ID> {
    void add(T t);

    void update(T t);

    T findById(ID id);

    void delete(T customer);

    void deleteById(ID id);

    void printAll();
}
