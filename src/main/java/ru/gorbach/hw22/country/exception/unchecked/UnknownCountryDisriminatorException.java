package ru.gorbach.hw22.country.exception.unchecked;

import ru.gorbach.hw22.common.business.exception.ReservationUncheckedException;

import static ru.gorbach.hw22.country.exception.CountryExceptionMeta.JDBC_UNKNOWN_COUNTRY_DISCRIMINATOR_ERROR;

public class UnknownCountryDisriminatorException extends ReservationUncheckedException {
    public UnknownCountryDisriminatorException() {
        this(JDBC_UNKNOWN_COUNTRY_DISCRIMINATOR_ERROR.getCode(), JDBC_UNKNOWN_COUNTRY_DISCRIMINATOR_ERROR.getDescription());
    }
    public UnknownCountryDisriminatorException(int code, String message) {
        super(message, code);
    }
}
