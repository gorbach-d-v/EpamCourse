package ru.gorbach.hw22.common.business.exception.jdbc;

import ru.gorbach.hw22.common.business.exception.ReservationUncheckedException;

import static ru.gorbach.hw22.common.business.exception.CommonExceptionMeta.JDBC_SQL_ERROR;

public class SqlError extends ReservationUncheckedException {

    public SqlError(Exception cause) {
        this(JDBC_SQL_ERROR.getDescription(), cause, JDBC_SQL_ERROR.getCode());
    }

    private SqlError(String message, Exception cause, int code) {
        super(message, cause, code);
    }
}
