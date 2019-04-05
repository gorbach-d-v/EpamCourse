package ru.gorbach.hw17.storage.initor.exception.checked;

import ru.gorbach.hw17.common.business.exception.ReservationCheckedException;

public class ParseXmlException extends ReservationCheckedException {

    public ParseXmlException(String message, Exception cause,int code) {
        super(message, cause, code);
    }
}
