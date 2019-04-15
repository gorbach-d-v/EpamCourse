package ru.gorbach.hw22.common.business.exception;


import static ru.gorbach.hw22.common.business.exception.CommonExceptionMeta.JDBC_CONNECTION_ACHIVE_ERROR;

public class ConnectionAchiveError extends ReservationUncheckedException {

    public ConnectionAchiveError(Exception cause) {
        super(JDBC_CONNECTION_ACHIVE_ERROR.getDescription(), cause, JDBC_CONNECTION_ACHIVE_ERROR.getCode());
    }
}
