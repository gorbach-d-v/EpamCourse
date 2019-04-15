package ru.gorbach.hw22.storage.initor.exception.checked;

import ru.gorbach.hw22.common.business.exception.ReservationCheckedException;

public class InitDataCheckedException extends ReservationCheckedException {

    public InitDataCheckedException(String message, int code) {
        super(message, code);
    }
}
