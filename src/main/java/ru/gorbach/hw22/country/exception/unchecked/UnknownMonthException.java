package ru.gorbach.hw22.country.exception.unchecked;

import ru.gorbach.hw22.common.business.exception.ReservationUncheckedException;

import static ru.gorbach.hw22.country.exception.CountryExceptionMeta.JDBC_UNKNOWN_MONTH_ERROR;

public class UnknownMonthException extends ReservationUncheckedException {
    public UnknownMonthException(String unknownMonth) {
        super(JDBC_UNKNOWN_MONTH_ERROR.getDescriptionAsFormatStr(unknownMonth), JDBC_UNKNOWN_MONTH_ERROR.getCode());
    }
}
