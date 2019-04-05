package ru.gorbach.hw17.storage.initor.exception.checked;

import ru.gorbach.hw17.common.business.exception.ReservationCheckedException;

public class InitDataCheckedException extends ReservationCheckedException {

    public InitDataCheckedException(String message, int code) {
        super(message, code);
    }
}
