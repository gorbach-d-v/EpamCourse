package ru.gorbach.hw20.city.exception.unchecked;

import ru.gorbach.hw20.city.exception.CityExceptionMeta;
import ru.gorbach.hw20.common.business.exception.ReservationUncheckedException;

public class DeleteCityException extends ReservationUncheckedException {

    public DeleteCityException(int code, String message) {
        super(message, code);
    }

    public DeleteCityException(CityExceptionMeta exceptionMeta) {
        super(exceptionMeta.getDescription(), exceptionMeta.getCode());
    }
}
