package ru.gorbach.hw20.country.exception.unchecked;

import ru.gorbach.hw20.common.business.exception.ReservationUncheckedException;
import ru.gorbach.hw20.country.exception.CountryExceptionMeta;

public class DeleteCountryException extends ReservationUncheckedException {

    public DeleteCountryException(int code, String message) {
        super(message, code);
    }

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getDescription(), exceptionMeta.getCode());
    }
}
