package ru.gorbach.hw17.country.exception.unchecked;

import ru.gorbach.hw17.country.exception.CountryExceptionMeta;
import ru.gorbach.hw17.common.business.exception.ReservationUncheckedException;

public class DeleteCountryException extends ReservationUncheckedException {

    public DeleteCountryException(int code, String message) {
        super(message, code);
    }

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getDescription(), exceptionMeta.getCode());
    }
}
