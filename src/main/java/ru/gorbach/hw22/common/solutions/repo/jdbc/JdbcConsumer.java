package ru.gorbach.hw22.common.solutions.repo.jdbc;

@FunctionalInterface
public interface JdbcConsumer<T> {
    void consume(T t) throws Exception;
}
