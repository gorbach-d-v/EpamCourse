package ru.gorbach.hw19.storage.initor.exception.checked;

import ru.gorbach.hw19.common.business.exception.ReservationCheckedException;

public class ParseXmlException extends ReservationCheckedException {

    public ParseXmlException(String message, Exception cause,int code) {
        super(message, cause, code);
    }
}
