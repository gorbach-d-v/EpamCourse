package ru.gorbach.hw13.city.exception.unchecked;

import ru.gorbach.hw13.city.exception.CityExceptionMeta;
import ru.gorbach.hw13.common.business.exception.ReservationUncheckedException;

public class DeleteCityException extends ReservationUncheckedException {

    public DeleteCityException(int code, String message) {
        super(message, code);
    }

    public DeleteCityException(CityExceptionMeta exceptionMeta) {
        super(exceptionMeta.getDescription(), exceptionMeta.getCode());
    }
}
