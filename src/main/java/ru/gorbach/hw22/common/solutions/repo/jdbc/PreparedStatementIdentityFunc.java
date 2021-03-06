package ru.gorbach.hw22.common.solutions.repo.jdbc;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface PreparedStatementIdentityFunc {
    PreparedStatement applyParamsAndGet(PreparedStatement ps) throws Exception;
}
